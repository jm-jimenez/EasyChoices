package net.ddns.dam2chema.easychoices

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import java.util.*

class MainActivity : AppCompatActivity() {

    private var elements = ArrayList<String>()
    private lateinit var listView: ListView
    private lateinit var spinner: Spinner
    private val spinnerValues = intArrayOf(1,2,3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.listView)
        spinner = findViewById(R.id.spinner)
        val listViewAdapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, elements)
        listView.adapter = listViewAdapter
        val spinnerAdapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_dropdown_item, spinnerValues.toTypedArray())
        spinner.adapter = spinnerAdapter
    }

    public fun btnClicked(view: View) {
        val layoutInflater = LayoutInflater.from(this)
        val userInputView = layoutInflater.inflate(R.layout.alert_layout, null)

        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(userInputView)

        val userInput = userInputView.findViewById<EditText>(R.id.userInput)
        alertDialogBuilder.setCancelable(true)
        alertDialogBuilder.setPositiveButton("Añadir", DialogInterface.OnClickListener { dialogInterface, i ->
            elements.add(userInput.text.toString())
        })

        alertDialogBuilder.create().show()
    }

    public fun doTheChoice(view: View) {
        var elementsToChooseFrom = ArrayList<String>(elements.size)
        elements.forEach{elementsToChooseFrom.add(it)}
        var choices = ArrayList<String>()
        var numberOfChoices = spinner.selectedItem as Int
        val random = Random()
        while (numberOfChoices > 0) {
            val index = random.nextInt(elementsToChooseFrom.size)
            choices.add(elementsToChooseFrom[index])
            elementsToChooseFrom.removeAt(index)
            numberOfChoices--
        }
        var result = "La elección ha sido"
        choices.forEach { result = "$result $it" }
        result = "$result."
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.setMessage(result)
        alertDialogBuilder.setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->  })
        alertDialogBuilder.create().show()
        
    }
}
