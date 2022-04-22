package com.example.contacts.presentation

import androidx.lifecycle.ViewModel
import com.example.contacts.data.ContactListRepositoryImpl
import com.example.contacts.domain.GetContactListUseCase

class ContactListViewModel : ViewModel() {
    private val repository = ContactListRepositoryImpl
    private val getContactListUseCase = GetContactListUseCase(repository)

    val contactList = getContactListUseCase.getContactList()
}