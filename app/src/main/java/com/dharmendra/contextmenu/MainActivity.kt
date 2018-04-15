package com.dharmendra.contextmenu

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    lateinit var array: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Find View By Id For ListView
        val listView = findViewById<ListView>(R.id.listView) as ListView

        //Array of String Which Contain Name of Persons.
        array = arrayOf("Sachin", "Vishal", "Rishu", "Krishank", "Vivek", "Jatin", "Raj", "Rajan", "Nikhil")

        //Creating Adapter
        val adp = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, array)

        //Set Adapter to ListView
        listView.adapter = adp

        //Register ListView for Context Menu
        registerForContextMenu(listView)

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)


        //Set Header of Context Menu
        menu!!.setHeaderTitle("Select Option")

        menu.add(0, v!!.id, 0, "Call")
        menu.add(0, v.id, 1, "SMS")
        menu.add(0, v.id, 2, "Email")
        menu.add(0, v.id, 3, "WhatsApp")


        /*
             menu.add get 4 Parameters

             1. grouId if you want to add multiple Group than for every group Id is Diffrent Here
                we have only One Group so We take 0(Zero) as GroupId

             2.v.id is our Item Id

             3. Set Order of Our Item(Position Of Item) if you Change order of Call to 1 and SMS to 0
                  than in Menu SMS Display First.

             4. Title to Display on Context menu
         */

    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

        //Get Order of Selected Item
        val selectedItemOrder = item!!.order

        //Get Title Of Selected Item
        val selectedItemTitle = item.title


        //To get Name of Person Click on ListView
        val info = item.menuInfo as AdapterContextMenuInfo
        val listPosition = info.position
        val name = array[listPosition]

        Toast.makeText(this@MainActivity, " " + selectedItemTitle + " " + name, Toast.LENGTH_LONG).show()

        return true
    }
}
