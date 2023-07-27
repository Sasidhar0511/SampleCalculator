package com.example.samplecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btnText = intent.getStringExtra(Constants.btnText)

        val btn = findViewById<Button>(R.id.button)
        btn.text = btnText

        val firstNum = findViewById<EditText>(R.id.etFirstNo)
        val secondNum = findViewById<EditText>(R.id.etSecondNo)

          var ansResult : Int? = null

        btn.setOnClickListener {
        val firstNo = firstNum.text.toString().toInt()
        val secNo = secondNum.text.toString().toInt()

            val intent = Intent()

            when(btnText){
                Constants.add -> { ansResult = firstNo+secNo }
                Constants.sub -> { ansResult = firstNo-secNo }
                Constants.mul -> { ansResult = firstNo*secNo }
                Constants.div -> {
                    if (secNo == 0) { Toast.makeText(this,R.string.DivideByZeroError,Toast.LENGTH_SHORT).show() }
                    else { ansResult = firstNo / secNo } }
            }

            if(ansResult != null) {
                intent.putExtra(Constants.firNo, firstNo)
                intent.putExtra(Constants.secNo, secNo)
                intent.putExtra(Constants.ansTxt, ansResult)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }


}