package com.example.contacts.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.R
import com.example.contacts.databinding.FragmentContactListBinding
import com.example.contacts.domain.Contact

class ContactListFragment : Fragment(R.layout.fragment_contact_list) {
    private var binding: FragmentContactListBinding? = null
    private var fragmentNavigator: FragmentNavigator? = null
    private var viewModel: ContactListViewModel? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigator) fragmentNavigator = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ContactListViewModel::class.java]
        viewModel?.contactList?.observe(viewLifecycleOwner) {
            showList(it)
        }
    }

    private fun showList(list: List<Contact>) {
        binding?.llContactList?.removeAllViews()
        for (i in list.indices) {
            val contactView = layoutInflater.inflate(
                R.layout.fragment_contact,
                binding?.llContactList,
                false
            )
            val firstName = contactView.findViewById<TextView>(R.id.tv_first_name)
            val lastName = contactView.findViewById<TextView>(R.id.tv_last_name)
            val phoneNumber = contactView.findViewById<TextView>(R.id.tv_phone_number)

            firstName.text = list[i].firstName
            lastName.text = list[i].lastName
            phoneNumber.text = list[i].phoneNumber.toString()
            contactView.tag = i
            binding?.llContactList?.addView(contactView)
            contactView.setOnClickListener {
                fragmentNavigator?.goFromContactListFragmentToContactFragment(
                    it.tag.toString().toInt()
                )
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        activity?.finish()
    }

    companion object {
        val FRAGMENT_CONTACT_LIST = "FRAGMENT_CONTACT_LIST"
        fun newInstance() = ContactListFragment()
    }
}