package com.bazuma.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastNumeric=false
    var lastDot=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("Commit 23 in add")
        println("This line merge in verBaz 21")
    }
    //step 6
    /*
     This method is used to display th output of calculation

     if example we have -50+20
     first we split the value into two
     second we ignore the negative and do calculation
     third we return the negative sign by using isEmpty()
     */
    fun onEqual(view: View){
        if (lastNumeric){
            //we have to know value of our text view
            var tvInputValue=tvinput.text.toString()
            var prefix=""
            try {
                //ignores negative sign
                if (tvInputValue.startsWith("-")){
                    prefix="-"
                    //ignores the - e.g 78 instead of -78
                    tvInputValue=tvInputValue.substring(1)
                }
                if (tvInputValue.contains("-")){
                    //split string
                    val splitvalue=tvInputValue.split("-")
                    var one=splitvalue[0]
                    var two=splitvalue[1]

                    //reaasign the negative sign not empty
                    if (!prefix.isEmpty()){
                        one=prefix+one
                    }
                    tvinput.text=removeZeroAfterDecimal((one.toDouble()-two.toDouble()).toString())
                }else if (tvInputValue.contains("+")){
                    val splitvalue=tvInputValue.split("+")
                    var one=splitvalue[0]
                    var two=splitvalue[1]

                    //reaasign the negative sign
                    if (!prefix.isEmpty()){
                        one=prefix+one
                    }
                    tvinput.text=removeZeroAfterDecimal((one.toDouble()+two.toDouble()).toString())
                }else if (tvInputValue.contains("*")){
                    val splitvalue=tvInputValue.split("*")
                    var one=splitvalue[0]
                    var two=splitvalue[1]

                    //reaasign the negative sign
                    if (!prefix.isEmpty()){
                        one=prefix+one
                    }
                    tvinput.text=removeZeroAfterDecimal((one.toDouble()*two.toDouble()).toString())
                }
                if (tvInputValue.contains("/")){
                    val splitvalue=tvInputValue.split("/")
                    var one=splitvalue[0]
                    var two=splitvalue[1]

                    //reaasign the negative sign
                    if (!prefix.isEmpty()){
                        one=prefix+one
                    }
                    tvinput.text=removeZeroAfterDecimal((one.toDouble()/two.toDouble()).toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }

        }
    }
    /*
    step 1
    cast the view
    The onDigit method is used to diplay the burron the user as click on tvinput
    Text View
       * */
    fun onDigit(view: View){
        tvinput.append((view as Button).text)
        lastNumeric=true
    }
    /*step 2
    Used to clear the screen when click CLR Button
    by setting tvinput.text=""
     lastNumeric and lastDot should be state to original state
       * */
    fun onClear(view: View){
        tvinput.text=""
        lastNumeric=false
        lastDot=false
    }
    /* step 3
    Used to display one decimal point at a time by adding
    lastNumeric which store last button as numeric
    lastDot which is used to store .
    lastNumeric=false this is because there is already a digit display
    lastDot=true because we have click dot hence will be true
       * */
    fun onDecimalPoint(view: View){
        if (lastNumeric && !lastDot){
            tvinput.append(".")
            lastNumeric=false
            lastDot=true
        }
    }
    private fun removeZeroAfterDecimal(result:String):String {
        var myvalue=result
        if (result.contains("0"))
            myvalue = result.substring(0, result.length - 2)
        return myvalue
    }
    //step 5
    fun onOperator(view: View){
        if (lastNumeric && !isOperatorAdded(tvinput.text.toString())){
                tvinput.append((view as Button).text)
            lastNumeric=false
            lastDot=false
            }
    }
    /*
    step 4
       This Method checks if the answer after calculation
       startWith - example -56
       then - is not treated as operator and allow us to check if any one
       of the operator has been added and if is true
       User cannot add another Operator
     */
    private fun isOperatorAdded(value:String):Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            //contains functionality of text view
            value.contains("/")||value.contains("*")||
                    value.contains("-")||value.contains("+")
        }
    }

}