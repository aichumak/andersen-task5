package com.example.contacts.presentation

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

        if (supportFragmentManager.backStackEntryCount == 0) {
            supportFragmentManager.beginTransaction().run {
                val fragment = ContactListFragment.newInstance()
                replace(
                    R.id.fragment_container,
                    fragment,
                    ContactListFragment.FRAGMENT_CONTACT_LIST
                )
                addToBackStack(ContactListFragment.FRAGMENT_CONTACT_LIST)
                commit()
            }
        } else {
            val index = supportFragmentManager.backStackEntryCount - 1
            val backEntry = supportFragmentManager.getBackStackEntryAt(index)
            val tag = backEntry.name
            supportFragmentManager.popBackStack(tag, 0)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        } else {
            goFromContactFragmentToContactListFragment()
        }
    }

    override fun goFromContactListFragmentToContactFragment(contactId: Int) {
        val fragmentContainer = if (binding.fragmentContainerForTablet == null) {
            R.id.fragment_container
        } else {
            R.id.fragment_container_for_tablet
        }
        supportFragmentManager.beginTransaction().run {
            val fragment = ContactFragment()
            fragment.arguments = Bundle().apply {
                putInt(ContactFragment.CONTACT_ID, contactId)
            }
            replace(fragmentContainer, fragment, ContactFragment.FRAGMENT_CONTACT_TAG)
            addToBackStack(ContactFragment.FRAGMENT_CONTACT_TAG)
            commit()
        }
    }

    override fun goFromContactFragmentToContactListFragment() {
        supportFragmentManager.popBackStack(ContactListFragment.FRAGMENT_CONTACT_LIST, 0)
    }
}