package com.example.contacts.domain

import androidx.lifecycle.LiveData

interface ContactListRepository {
    fun editContact(contact: Contact)
    fun getContactList(): LiveData<List<Contact>>
    fun getContact(contactId: Int): Contact
}