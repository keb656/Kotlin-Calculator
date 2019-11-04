package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isNumber:Boolean = false
    var isFinish:Boolean = false
    var isDivision:Boolean = false
    //var isZero:Boolean = false

    //val dialogView: View = layoutInflater.inflate(R.layout.custom_dialog, null)
    lateinit var customDialog: CustomDialog

    //val dialogView2: View = layoutInflater.inflate(R.layout.custom_dialog2, null)
    lateinit var customDialog2: CustomDialog2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initButton()

    }

    fun initButton(){
        val num = arrayOf(btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9)
        for (i in num){
            i.setOnClickListener(listenerNumber())
        }

        val calc = arrayOf(btn_plus, btn_minus, btn_multiply, btn_division, btn_sum, btn_clear)
        for (i in calc){
            i.setOnClickListener(listenerCalculator())
        }
/*
        btn_0.setOnClickListener(listenerNumber())
        btn_1.setOnClickListener(listenerNumber())
        btn_2.setOnClickListener(listenerNumber())
        btn_3.setOnClickListener(listenerNumber())
        btn_4.setOnClickListener(listenerNumber())
        btn_5.setOnClickListener(listenerNumber())
        btn_6.setOnClickListener(listenerNumber())
        btn_7.setOnClickListener(listenerNumber())
        btn_8.setOnClickListener(listenerNumber())
        btn_9.setOnClickListener(listenerNumber())

        btn_plus.setOnClickListener(listenerCalculator())
        btn_minus.setOnClickListener(listenerCalculator())
        btn_multiply.setOnClickListener(listenerCalculator())
        btn_division.setOnClickListener(listenerCalculator())

        btn_sum.setOnClickListener(listenerCalculator())
        btn_clear.setOnClickListener(listenerCalculator())
*/
    }

    fun listenerNumber(): View.OnClickListener {
        val listener = View.OnClickListener {view ->

            if(isFinish){
                tv_input.setText("")
                tv_result.setText("")
                isFinish = false
            }

            when (view.id) {
                R.id.btn_0 -> {
                    //isZero = true
                    val dialogView2: View = layoutInflater.inflate(R.layout.custom_dialog2, null)
                    ZeroFilter(dialogView2)
                }
                R.id.btn_1 -> {
                    //isZero = false
                    tv_input.append("1")
                }
                R.id.btn_2 -> {
                    //isZero = false
                    tv_input.append("2")
                }
                R.id.btn_3 -> {
                    //isZero = false
                    tv_input.append("3")
                }
                R.id.btn_4 -> {
                    //isZero = false
                    tv_input.append("4")
                }
                R.id.btn_5 -> {
                    //isZero = false
                    tv_input.append("5")
                }
                R.id.btn_6 -> {
                    //isZero = false
                    tv_input.append("6")
                }
                R.id.btn_7 -> {
                    //isZero = false
                    tv_input.append("7")
                }
                R.id.btn_8 -> {
                    //isZero = false
                    tv_input.append("8")
                }
                R.id.btn_9 -> {
                    //isZero = false
                    tv_input.append("9")
                }
            }
            isNumber = true
        }
        return listener
    }

    fun listenerCalculator(): View.OnClickListener {
        val listener = View.OnClickListener { view ->


            when(view.id){
                R.id.btn_plus -> {
                    isDivision = false
                    val dialogView: View = layoutInflater.inflate(R.layout.custom_dialog, null)
                    Result("+", dialogView)
                }
                R.id.btn_minus -> {
                    isDivision = false
                    val dialogView: View = layoutInflater.inflate(R.layout.custom_dialog, null)
                    Result("-", dialogView)
                }
                R.id.btn_multiply -> {
                    isDivision = false
                    val dialogView: View = layoutInflater.inflate(R.layout.custom_dialog, null)
                    Result("*", dialogView)
                }
                R.id.btn_division -> {
                    isDivision = true
                    val dialogView: View = layoutInflater.inflate(R.layout.custom_dialog, null)
                    Result("/", dialogView)
                }
                R.id.btn_sum -> {
                    isDivision = false
                    val dialogView: View = layoutInflater.inflate(R.layout.custom_dialog, null)
                    Result("=", dialogView)
                    isFinish = true
                }
                R.id.btn_clear -> {
                    tv_input.setText("")
                    tv_result.setText("")
                }
            }
            isNumber = false
            //isZero = false
        }
        return listener
    }

    fun Calculator(strVal : String) :String {
        var num = strVal.split("+", "-", "*", "/")

        var oper = mutableListOf<String>()
        for (i in strVal){
            if ((i == '+') or (i == '-') or (i == '*') or (i == '/')){
                oper.add(i.toString())
            }
        }

        var calcList = mutableListOf<Int>()
        calcList.add(num[0].toInt())
        var temp = 0

        for (i in oper) {
            if (i == "+"){
                calcList.add(num[oper.indexOf(i)+1].toInt())

            }else if (i == "-"){
                calcList.add(-1 * (num[oper.indexOf(i)+1].toInt()))

            }else if (i == "*"){
                temp = calcList.last()
                calcList.remove(calcList.last())
                calcList.add(temp * (num[oper.indexOf(i)+1].toInt()))

            }else if (i == "/"){
                temp = calcList.last()
                calcList.remove(calcList.last())
                calcList.add(temp / (num[oper.indexOf(i)+1].toInt()))
            }
        }

        var sumVal = 0

        for (i in calcList) {
            sumVal += i
        }
        return sumVal.toString()
    }

    fun ZeroFilter(dialogView2: View){
        if (isDivision) {
            customDialog2 = CustomDialog2()
            customDialog2.AlertDialog2(this, dialogView2)
        }else{
            tv_input.append("0")
        }
    }

    fun Result(operator: String, dialogView: View){
        if (isNumber) {
            if (operator == "=") {//계산할때에는 별도의 로직
                tv_history.setText("")
                var inputText = tv_input.text.toString()
                var answer = Calculator(inputText)
                tv_result.setText(answer)
                tv_history.setText(inputText+" = "+answer)
            } else{
                tv_input.append(operator)
                tv_result.setText("")
            }
        } else {
            customDialog = CustomDialog()
            customDialog.AlertDialog(this, dialogView)
        }
    }

}
