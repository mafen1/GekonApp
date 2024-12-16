import android.app.Dialog
import android.os.Bundle
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.gekonapp.R


class HeightPickerDialogFragment(val array: Array<String>) : DialogFragment() {

    private var listener: ((String) -> Unit)? = null


    fun setOnHeightSelectedListener(listener: (String) -> Unit) {
        this.listener = listener
    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            // Layout for the dialog
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_height_picker, null)

            // Get the NumberPicker and set custom values
            val numberPicker: NumberPicker = dialogView.findViewById(R.id.numberPicker)

            numberPicker.minValue = 0
            numberPicker.maxValue = array.size - 1
            numberPicker.displayedValues = array

            builder.setView(dialogView)
                .setPositiveButton("OK") { dialog, id ->
                    val selectedValue = array[numberPicker.value]
                    listener?.invoke(selectedValue)

                }
                .setNegativeButton("Cancel") { dialog, id ->
                    dialog.cancel()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

