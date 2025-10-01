package com.example.myshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myshop.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {
    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            // Ambil data productName dari HomeFragment
            val args: CheckoutFragmentArgs by navArgs()
            txtProductName.text = args.productName

            // Klik alamat → pindah ke AddressFragment
            edtAddress.setOnClickListener {
                val action =
                    CheckoutFragmentDirections.actionCheckoutFragmentToAddressFragment()
                findNavController().navigate(action)
            }


            // Terima data alamat dari AddressFragment
            findNavController().currentBackStackEntry
                ?.savedStateHandle
                ?.getLiveData<String>("address")
                ?.observe(viewLifecycleOwner) { res ->
                    edtAddress.setText(res)
                }

            // Klik selesai → kembali ke Home
            btnDone.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
