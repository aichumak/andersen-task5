package com.example.contacts.presentation

interface FragmentNavigator {
    fun goFromContactListFragmentToContactFragment(contactId: Int)
    fun goFromContactFragmentToContactListFragment()
}