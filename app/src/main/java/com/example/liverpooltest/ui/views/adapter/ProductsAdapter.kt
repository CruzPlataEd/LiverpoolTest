package com.example.liverpooltest.ui.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liverpooltest.data.model.Products
import com.example.liverpooltest.databinding.ProductElementBinding
import com.squareup.picasso.Picasso


class ProductsAdapter(private val dataSet : List<Products>): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private lateinit var binding: ProductElementBinding
    class ViewHolder(private val binding: ProductElementBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Products){
            binding.product = product
            if(product.promoPrice == product.price) {
                binding.promoPrice.text = "$ " + product.promoPrice
            }else{
                binding.promoPrice.text = "$ " + product.promoPrice
                binding.normalPrice.text = "$ " + product.price
            }
            Picasso.get().load(product.largeImage).into(binding.imgProduct)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        binding = ProductElementBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val product = dataSet[position]
        viewHolder.bind(product)
    }

    override fun getItemCount() = dataSet.size

}