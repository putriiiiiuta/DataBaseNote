package putriiiiiuta.androidlima.databasenote

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import putriiiiiuta.androidlima.databasenote.datastore.ManagerNote
import putriiiiiuta.androidlima.databasenote.room.Note
import putriiiiiuta.androidlima.databasenote.room.NoteDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class AddNoteFragment : Fragment() {
    private var noteDatabase : NoteDatabase? = null
    lateinit var noteManager: ManagerNote
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        noteDatabase = NoteDatabase.getinstance(requireContext())
        noteManager = ManagerNote(requireContext())
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_add.setOnClickListener {
            val judul = add_judul.text.toString()
            val isi = add_isi.text.toString()
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            val formatted = current.format(formatter)
            tambahnote(Note(null,judul,formatted.toString(),isi))
        }
    }
    fun tambahnote(note: Note){
        GlobalScope.async {
            val result = noteDatabase?.notedao()?.addNote(note)
            requireActivity().runOnUiThread(){
                if(result != 0.toLong()){
                    Toast.makeText(requireContext(),"note berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(requireView()).navigate(R.id.action_addNoteFragment_to_noteFragment)
                }
            }
        }
    }

}