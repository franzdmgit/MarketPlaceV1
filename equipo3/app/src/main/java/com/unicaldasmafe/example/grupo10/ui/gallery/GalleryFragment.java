package com.unicaldasmafe.example.grupo10.ui.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unicaldasmafe.example.grupo10.R;
import com.unicaldasmafe.example.grupo10.databinding.FragmentGalleryBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GalleryFragment extends Fragment {

    private TextView txt_galeria;
    private Spinner spn_categorias;

    private RecyclerView rev_productos;

    private RecyclerView.Adapter mAdapter;


    private String productos=  "[{\"nombre\":\"Saco mujer\",\"categoria\":\"Ropa Mujer\",\"precio\":60000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal A\",\"direccion\":\"Diección A\",\"encargado\":{\"nombre\":\"Encargado A\"}},{\"nombre\":\"Sucursal B\",\"direccion\":\"Diección B\",\"encargado\":{\"nombre\":\"Encargado B\"}}]},{\"nombre\":\"Guayos\",\"categoria\":\"Futbol\",\"precio\":40000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal C\",\"direccion\":\"Diección C\",\"encargado\":{\"nombre\":\"Encargado C\"}},{\"nombre\":\"Sucursal D\",\"direccion\":\"Diección D\",\"encargado\":{\"nombre\":\"Encargado D\"}}]},{\"nombre\":\"Balón\",\"categoria\":\"Futbol\",\"precio\":40000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Casco\",\"categoria\":\"Futbol\",\"precio\":40000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Medias\",\"categoria\":\"Futbol\",\"precio\":40000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Canilleras\",\"categoria\":\"Futbol\",\"precio\":40000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Pelota\",\"categoria\":\"Futbol\",\"precio\":40000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Tobilleras\",\"categoria\":\"Futbol\",\"precio\":40000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Guantes\",\"categoria\":\"Futbol\",\"precio\":40000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal A\",\"direccion\":\"Diección A\",\"encargado\":{\"nombre\":\"Encargado A\"}},{\"nombre\":\"Sucursal B\",\"direccion\":\"Diección B\",\"encargado\":{\"nombre\":\"Encargado B\"}}]},{\"nombre\":\"Guayos\",\"categoria\":\"Futbol\",\"precio\":40000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal C\",\"direccion\":\"Diección C\",\"encargado\":{\"nombre\":\"Encargado C\"}},{\"nombre\":\"Sucursal D\",\"direccion\":\"Diección D\",\"encargado\":{\"nombre\":\"Encargado D\"}}]},{\"nombre\":\"Balón\",\"categoria\":\"Futbol\",\"precio\":40000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Casco\",\"categoria\":\"Futbol\",\"precio\":40000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Medias\",\"categoria\":\"Futbol\",\"precio\":40000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Canilleras\",\"categoria\":\"Futbol\",\"precio\":40000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Pelota\",\"categoria\":\"Futbol\",\"precio\":40000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Tobilleras\",\"categoria\":\"Futbol\",\"precio\":40000,\"enstock\":true,\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]}]";



    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        txt_galeria = root.findViewById(R.id.txt_galeria);
        txt_galeria.setText("hola mundo");

        spn_categorias= root.findViewById(R.id.spn_categorias);

        String[] categorias = new String[]{"Ropa Mujer", "Ropa Hombre","Accesorios","Zapatos Hombre"};

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,
                categorias);

        spn_categorias.setAdapter(adaptador);

        spn_categorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categoria = spn_categorias.getSelectedItem().toString();
                txt_galeria.setText(categoria);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rev_productos = root.findViewById(R.id.rev_productos);
        /*formas de diagramar*/
        rev_productos.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*rev_productos.setLayoutManager(new GridLayoutManager(getActivity(), 2));*/

        try {
            JSONArray jsonproductos = new JSONArray(productos);

            mAdapter = new ProductosAdapter(jsonproductos, getActivity());

            rev_productos.setAdapter(mAdapter);


            JSONObject producto0 = jsonproductos.getJSONObject(0);

            String nombre = producto0.getString("nombre");

            JSONArray sucursal = producto0.getJSONArray("sucursal");

            JSONObject sucursal1 = sucursal.getJSONObject(1);

            String nombresucursal = sucursal1.getString("nombre");

            Toast.makeText(getActivity(), "NOMBRE:"+ nombresucursal, Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {

    private JSONArray productos;
    private Activity miActividad;

    public ProductosAdapter(JSONArray productos, Activity miActividad) {
        this.productos = productos;
        this.miActividad = miActividad;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_productos, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        try {
            Log.e("POSICION", "POS: " + position);
            String nombre = this.productos.getJSONObject(position).getString("nombre");
            String categoria = this.productos.getJSONObject(position).getString("categoria");
            int precio = this.productos.getJSONObject(position).getInt("precio");

            holder.tev_item_name.setText(nombre);
            holder.tev_item_categoria.setText(categoria);
            holder.tev_item_precio.setText("$" + precio);
        } catch (JSONException e) {
            holder.tev_item_name.setText("error");
        }

    }

    @Override
    public int getItemCount() {
        Log.e("CANTIDAD_PRODUCTOS", "" + this.productos.length());
        return this.productos.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tev_item_name;
        private TextView tev_item_categoria;
        private TextView tev_item_precio;
        public ViewHolder(View v) {
            super(v);
            tev_item_name = v.findViewById(R.id.tev_item_name);
            tev_item_categoria = v.findViewById(R.id.tev_item_categoria);
            tev_item_precio = v.findViewById(R.id.tev_item_precio);

        }
    }
}