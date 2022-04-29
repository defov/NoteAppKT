package com.example.noteapp.feature_note.domain.use_case

import com.example.noteapp.feature_note.data.repository.FakeNoteRepository
import com.example.noteapp.feature_note.domain.model.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNoteTest {

    private lateinit var getNote: GetNote
    private lateinit var fakeRepository: FakeNoteRepository

    @Before
    fun setUp() {
        fakeRepository = FakeNoteRepository()
        getNote = GetNote(fakeRepository)
    }

    @Test
    fun `find note by id should return the node`() = runBlocking {
        val note = Note(
            title = "test",
            content = "test",
            timestamp = 1L,
            color = 1,
            id = 1
        )
        fakeRepository.insertNote(note)
        val foundedNote = getNote(1)
        assertThat(foundedNote).isEqualTo(note)
    }

}