package com.example.samplecalculator


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val tvResText = "tvResText"
    private val addBtnVisibility = "btnAddVisibility"

    private lateinit var btnAdd : Button
    private lateinit var btnSub : Button
    private lateinit var btnMul : Button
    private lateinit var btnDiv : Button
    private lateinit var btnReset : Button
    private lateinit var tvRes : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         btnAdd = findViewById(R.id.btnAdd)
         btnSub = findViewById(R.id.btnSub)
         btnMul = findViewById(R.id.btnMul)
         btnDiv = findViewById(R.id.btnDiv)
        btnReset = findViewById(R.id.btnReset)
         tvRes = findViewById(R.id.tvResult)

        btnAdd.setOnClickListener (this)
        btnSub.setOnClickListener (this)
        btnMul.setOnClickListener (this)
        btnDiv.setOnClickListener (this)

        btnReset.setOnClickListener {
            btnAdd.visibility = View.VISIBLE
            btnSub.visibility = View.VISIBLE
            btnMul.visibility = View.VISIBLE
            btnDiv.visibility = View.VISIBLE

            btnReset.visibility = View.GONE
            tvRes.visibility = View.GONE
        }

        if(savedInstanceState != null){

            val addButtonVisibility = savedInstanceState.getInt(addBtnVisibility)

            if(addButtonVisibility == View.VISIBLE){
                btnAdd.visibility = View.VISIBLE
                btnSub.visibility = View.VISIBLE
                btnMul.visibility = View.VISIBLE
                btnDiv.visibility = View.VISIBLE
                btnReset.visibility = View.GONE
                tvRes.visibility = View.GONE
            }
            else{
                val tvResult = savedInstanceState.getString(tvResText)
                tvRes.text = tvResult
                tvRes.visibility = View.VISIBLE
                btnReset.visibility = View.VISIBLE
                btnAdd.visibility = View.GONE
                btnSub.visibility = View.GONE
                btnMul.visibility = View.GONE
                btnDiv.visibility = View.GONE

            }

        }
    }

    override fun onClick(v: View?) {
        val intent = Intent(this,SecondActivity::class.java)
        lateinit var btnText : String

        when(v?.id){
            R.id.btnAdd ->{ btnText = Constants.add ; R.string.ans = R.string.AddText }
            R.id.btnSub ->{ btnText = Constants.sub ; R.string.ans = R.string.SubText }
            R.id.btnMul ->{ btnText = Constants.mul ; R.string.ans = R.string.MulText }
            R.id.btnDiv ->{ btnText = Constants.div ; R.string.ans = R.string.DivText }
        }

        intent.putExtra(Constants.btnText,btnText)
        resultLauncher.launch(intent)
    }


    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->

        if(result.resultCode == Activity.RESULT_OK){

            btnAdd.visibility = View.GONE
            btnSub.visibility = View.GONE
            btnMul.visibility = View.GONE
            btnDiv.visibility = View.GONE

            val data : Intent? = result.data
            val firstNo = data?.getIntExtra(Constants.firNo,0)
            val secNo = data?.getIntExtra(Constants.secNo,0)
            val ansResult = data?.getIntExtra(Constants.ansTxt,0)
            btnReset.visibility = View.VISIBLE
            tvRes.visibility = View.VISIBLE

            tvRes.text = getString(R.string.ans,firstNo,secNo,ansResult)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(tvResText,tvRes.text.toString())
        outState.putInt(addBtnVisibility,btnAdd.visibility)

    }
}