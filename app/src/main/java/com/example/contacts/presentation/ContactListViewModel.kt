package com.example.contacts.presentation

import androidx.lifecycle.ViewModel
import com.example.contacts.data.ContactListRepositoryImpl
import com.example.contacts.domain.Contact
import com.example.contacts.domain.GetContactListUseCase
import com.example.contacts.domain.GetContactUseCase

class ContactListViewModel : ViewModel() {
    private val repository = ContactListRepositoryImpl
    private val getContactListUseCase = GetContactListUseCase(repository)
    private val getContactUseCase = GetContactUseCase(repository)

    val contactList = getContactListUseCase.getContactList()

    fun getContact(contactId: Int): Contact {
        return getContactUseCase.getContact(contactId)
    }

}