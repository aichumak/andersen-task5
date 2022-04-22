package com.example.contacts.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.contacts.domain.Contact
import com.example.contacts.domain.ContactListRepository

object ContactListRepositoryImpl : ContactListRepository {

    private var dataBaseLiveData = MutableLiveData<List<Contact>>()
    private val dataBase = sortedSetOf<Contact>({ o1, o2 -> o1.id.compareTo(o2.id) })

    init {
        for (i in 0 until 9) {
            dataBase.add(Contact(i, "Mike$i", "Jordan$i", (1452580 + i)))
        }
        updateDataBaseLiveData()
    }

    override fun editContact(contact: Contact) {
        val oldContact = getContact(contact.id)
        dataBase.remove(oldContact)
        dataBase.add(contact)
        updateDataBaseLiveData()
    }

    override fun getContactList(): LiveData<List<Contact>> {
        return dataBaseLiveData
    }

    override fun getContact(contactId: Int): Contact {
        return dataBase.find {
            it.id == contactId
        } ?: throw RuntimeException("Contact with id:$contactId not found")
    }

    private fun updateDataBaseLiveData() {
        dataBaseLiveData.value = dataBase.toList()
    }
}