package putriiiiiuta.androidlima.databasenote

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_note_app.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import putriiiiiuta.androidlima.databasenote.adapter.AdapterNote
import putriiiiiuta.androidlima.databasenote.datastore.ManagerNote
import putriiiiiuta.androidlima.databasenote.room.NoteDatabase

@DelicateCoroutinesApi
@Suppress("DeferredResultUnused")
class ProfileFragment : Fragment() {

    private var noteDatabase : NoteDatabase? = null
    private lateinit var noteManager: ManagerNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        noteDatabase = NoteDatabase.getinstance(requireContext())
        noteManager = ManagerNote(requireContext())
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNote()
        btn_add.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_noteFragment_to_addNoteFragment)
        }
        avatar.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_noteFragment_to_profileFragment)
        }
    }

    @DelicateCoroutinesApi
    fun getNote(){
        GlobalScope.launch {
            val result = noteDatabase?.notedao()?.getNote()
            requireActivity().runOnUiThread {
                if (result != null) {
                    rvNote.layoutManager = LinearLayoutManager(requireContext())
                    rvNote.adapter = AdapterNote(result) {
                        val bundle = bundleOf("detail" to it)
                        Navigation.findNavController(requireView()).navigate(R.id.action_noteFragment_to_detailFragment, bundle)
                    }
                }
            }
        }
    }

}