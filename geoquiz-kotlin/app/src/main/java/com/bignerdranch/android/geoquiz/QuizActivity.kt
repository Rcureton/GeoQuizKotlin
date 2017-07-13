package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.arch.lifecycle.LifecycleActivity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bignerdranch.android.geoquiz.databinding.ActivityQuizBinding

class QuizActivity : LifecycleActivity() {

    private lateinit var mBinding: ActivityQuizBinding
    private val KEY_INDEX = "index"
    private val TAG = "QuizActivity"
    private var mCurrentIndex = 0
    private val REQUEST_CODE = 0
    private var mIsCheater : Boolean = false

    private val mQuestionBank = arrayOf(Question(R.string.question_australia, true),
            Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_americas, true),
            Question(R.string.question_asia, true))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0)
        }

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_quiz)

        mBinding.trueButton.setOnClickListener { checkAnswer(true) }

        mBinding.falseButton.setOnClickListener {  checkAnswer(false) }

        mBinding.nextButton.setOnClickListener {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            mIsCheater = false
            updateQuestion()
        }

        mBinding.cheatButton.setOnClickListener {
            val answerIsTrue = mQuestionBank[mCurrentIndex].mAnswerTrue
            val intent = CheatActivity.newIntent(this, answerIsTrue)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        if (requestCode == REQUEST_CODE) {
            if (data == null) {
                return
            }
            mIsCheater = CheatActivity.wasAnswerShown(data)
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle?) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSavedInstanceState")
        savedInstanceState?.putInt(KEY_INDEX, mCurrentIndex)
        Log.d(TAG, mCurrentIndex.toString())
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
    }

    fun updateQuestion() {
        val question = mQuestionBank[mCurrentIndex].mTextResId
        mBinding.questionText.setText(question)
    }

    fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue = mQuestionBank[mCurrentIndex].mAnswerTrue

        var messageResId = 0

        if (mIsCheater) {
            messageResId = R.string.judgment_toast
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast
            } else {
                messageResId = R.string.incorrect_toast
            }
        }


        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

}
