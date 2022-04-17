package com.example.contacts.presentation

import androidx.lifecycle.ViewModel
import com.example.contacts.data.ContactListRepositoryImpl
import com.example.contacts.domain.Contact
import com.example.contacts.domain.EditContactUseCase
import com.example.contacts.domain.GetContactUseCase

class ContactViewModel : ViewModel() {
    private val repository = ContactListRepositoryImpl
    private val getContactUseCase = GetContactUseCase(repository)
    private val editContactUseCase = EditContactUseCase(repository)

    fun getContact(contactId: Int): Contact {
        return getContactUseCase.getContact(contactId)
    }

    fun editContact(contact: Contact?) {
        contact?.let {
            editContactUseCase.editContact(it)
        }
    }

}