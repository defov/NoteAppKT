package com.example.noteapp.feature_note.domain.use_case

import com.example.noteapp.feature_note.data.repository.FakeNoteRepository
import com.example.noteapp.feature_note.domain.model.InvalidNoteException
import com.example.noteapp.feature_note.domain.model.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddNoteTest {

    private lateinit var addNote: AddNote;
    private lateinit var fakeRepository: FakeNoteRepository;

    @Before
    fun setup() {
        fakeRepository = FakeNoteRepository()
        addNote = AddNote(fakeRepository)
    }

    @Test
    fun `Add note with correct data, inserts into repository`() = runBlocking {
        val note = Note (
            title = "TEST",
            content = "TEST",
            timestamp = 1L,
            color = 1,
            id = 1
        )

        addNote(note)

        assertThat(fakeRepository.getNoteById(1)).isEqualTo(note)
    }

    @Test
    fun `Add note without title, throws an exception`() = runBlocking {
        val note = Note (
            title = "",
            content = "TEST",
            timestamp = 1L,
            color = 1,
            id = 1
        )

        try {
            addNote(note)
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(InvalidNoteException::class.java)
        } finally {
            val notes = fakeRepository.getNotes().first()
            assertThat(notes).isEmpty()
        }

    }

    @Test
    fun `Add note without content, throws an exception`() = runBlocking {
        val note = Note (
            title = "TEST",
            content = "",
            timestamp = 1L,
            color = 1,
            id = 1
        )

        try {
            addNote(note)
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(InvalidNoteException::class.java)
        } finally {
            val notes = fakeRepository.getNotes().first()
            assertThat(notes).isEmpty()
        }
    }


}