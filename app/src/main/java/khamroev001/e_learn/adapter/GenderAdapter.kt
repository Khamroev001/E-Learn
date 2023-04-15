package khamroev001.e_learn.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class GenderAdapter(context: Context, resource: Int, genders: Array<String>) :
    ArrayAdapter<String>(context, resource, genders) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = getItem(position)
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = getItem(position)
        return view
    }
}