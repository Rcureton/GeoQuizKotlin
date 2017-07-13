package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.arch.lifecycle.LifecycleActivity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.bignerdranch.android.geoquiz.databinding.ActivityCheatBinding

class CheatActivity : LifecycleActivity() {

    var mAnswerIsTrue: Boolean = false
    private lateinit var mBinding: ActivityCheatBinding

            companion

    object {
        private val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"
                private val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"


                fun newIntent(context: Context, answerIsTrue: Boolean): Intent {
            val intent = Intent(context, CheatActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            return intent
        }

                fun wasAnswerShown(result: Intent): Boolean {
                    return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)
                }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cheat)

        mAnswerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        mBinding.showAnswerButton.setOnClickListener {
            if (mAnswerIsTrue) {
                mBinding.answerTextView.setText(R.string.true_button)
            } else {
                mBinding.answerTextView.setText(R.string.false_button)
            }

        }
    }

    fun setAnswerShownResult(isAnswerShown: Boolean) {
        val intent = Intent()
        intent.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        setResult(Activity.RESULT_OK, intent)
    }
}
