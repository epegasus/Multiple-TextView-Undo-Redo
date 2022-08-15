package dev.pegasus.regret

import android.widget.TextView
import dev.pegasus.regret.helper.Regret
import dev.pegasus.regret.interfaces.RegretListener

class RegretManager : RegretListener {

    private val regret = Regret(this)
    private var textView: TextView? = null

    private var previousText = ""
    private var isUndoing = false

    fun setView(textView: TextView) {
        this.textView = textView
    }

    fun setPreviousText(previousText: String) {
        this.previousText = previousText
    }

    fun setNewText(newText: String) {
        if (!isUndoing) {
            regret.add("key", previousText, newText)
            previousText = newText
        }
    }

    fun undo() {
        if (regret.canUndo()) {
            isUndoing = true
            regret.undo()
            isUndoing = false
        }
    }

    fun redo() {
        if (regret.canRedo()) {
            isUndoing = true
            regret.redo()
            isUndoing = false
        }
    }

    override fun onDo(key: String?, value: Any?) {
        when (key) {
            "key" -> textView?.text = value.toString()
        }
    }

    override fun onCanDo(canUndo: Boolean, canRedo: Boolean) {

    }
}