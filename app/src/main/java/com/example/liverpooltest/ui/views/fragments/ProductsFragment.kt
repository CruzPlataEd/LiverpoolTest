package com.example.liverpooltest.ui.views.fragments

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.liverpooltest.R
import com.example.liverpooltest.data.model.Products
import com.example.liverpooltest.databinding.FragmentProductsBinding
import com.example.liverpooltest.ui.viewmodel.ProductsViewModel
import com.example.liverpooltest.ui.views.adapter.ProductsAdapter

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private val productsViewModel : ProductsViewModel by activityViewModels()
    private lateinit var productAdapter : ProductsAdapter
    private var productList = emptyList<Products>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = productsViewModel
        productsViewModel.onCreate()

        setView()


        return binding.root
    }

    fun setView(){
        productsViewModel.sortOptions.observe(viewLifecycleOwner, Observer {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it.toTypedArray())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.filter.adapter = adapter
        })

        binding.CantidadTopRated.setOnClickListener {
            if (isConnected(requireContext()))
                colocarCantidadProducto()
        }

        binding.filter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                if (productsViewModel.sortSelected != productsViewModel.getSortRequest(position)) {
                    productsViewModel.sortSelected = productsViewModel.getSortRequest(position)
                    productsViewModel.updateInfo()
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        productsViewModel.products.observe(viewLifecycleOwner, Observer {
            productAdapter = ProductsAdapter(it)
            binding.RVProducts.adapter = productAdapter
            binding.RVProducts.layoutManager = LinearLayoutManager(requireContext())
            binding.RVProducts.setHasFixedSize(true)
        })
    }

    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }

    private fun colocarCantidadProducto(){
        val alertDialog = AlertDialog.Builder(context,R.style.CustomDialogTheme)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_cant, null)
        val cantidadText = view.findViewById<EditText>(R.id.cantidad_producto)
        alertDialog.apply {
            setView(view)
            setCancelable(false)
            setTitle("Enter the page")
            setPositiveButton("Accept") { dialog, id ->
                if (cantidadText.text.isNullOrBlank() || cantidadText.text.toString().toInt() == 0) {
                    Toast.makeText(requireContext(),"Enter a number",Toast.LENGTH_LONG).show()
                } else {
                    binding.CantidadTopRated.text = cantidadText.text.toString()
                    productsViewModel.page = cantidadText.text.toString().toInt()
                    productsViewModel.updateInfo()
                }
            }
            setNegativeButton("Cancelar") { dialog, id -> dialog.cancel() }
        }.create().show()
    }
}