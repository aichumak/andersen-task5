package com.example.contacts.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.R
import com.example.contacts.databinding.ActivityFragmentBinding

class FragmentActivity : AppCompatActivity(), FragmentNavigator {
    private lateinit var binding: ActivityFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().run {
            val fragment = ContactListFragment.newInstance()
            replace(R.id.fragment_container, fragment, ContactListFragment.FRAGMENT_CONTACT_LIST)
            addToBackStack(ContactListFragment.FRAGMENT_CONTACT_LIST)
            commit()
        }
    }

    override fun goFromContactListFragmentToContactFragment(contactId: Int) {
        supportFragmentManager.beginTransaction().run {
            val fragment = ContactFragment()
            fragment.arguments = Bundle().apply {
                putInt(ContactFragment.CONTACT_ID, contactId)
            }
            replace(R.id.fragment_container, fragment, ContactFragment.FRAGMENT_CONTACT_TAG)
            addToBackStack(ContactFragment.FRAGMENT_CONTACT_TAG)
            commit()
        }
    }

    override fun goFromContactFragmentToContactListFragment() {
        supportFragmentManager.popBackStack(ContactListFragment.FRAGMENT_CONTACT_LIST, 0)
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, FragmentActivity::class.java)

    }
}