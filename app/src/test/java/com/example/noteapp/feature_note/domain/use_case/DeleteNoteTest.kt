package com.example.noteapp.feature_note.domain.use_case

import com.example.noteapp.feature_note.data.repository.FakeNoteRepository
import com.example.noteapp.feature_note.domain.model.Note
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteNoteTest {

    private lateinit var deleteNote: DeleteNote
    private lateinit var fakeRepository: FakeNoteRepository

    @Before
    fun setUp() {
        fakeRepository = FakeNoteRepository()
        deleteNote = DeleteNote(fakeRepository)
    }

    @Test
    fun `delete note, should not find the note anymore`() = runBlocking {
        val note = Note(
            title = "test",
            content = "test",
            timestamp = 1L,
            color = 1,
            id = 1
        )
        fakeRepository.insertNote(note)
        Truth.assertThat(fakeRepository.getNotes().first()).isNotEmpty()
        deleteNote(note)
        Truth.assertThat(fakeRepository.getNotes().first()).isEmpty()
    }

}