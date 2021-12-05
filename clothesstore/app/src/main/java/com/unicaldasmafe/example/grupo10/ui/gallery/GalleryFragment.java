package com.unicaldasmafe.example.grupo10.ui.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

import com.bumptech.glide.Glide;
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


    private String productos=   "[{\"nombre\":\"Capucha corta unicolor con cremallera\",\"categoria\":\"Futbol\",\"precio\":63696,\"enstock\":true,\"imagen\":\"https://img.ltwebstatic.com/images3_pi/2021/10/09/1633756297ba16fbca954ca41f790316269f3a08e0_thumbnail_750x.webp\"},{\"nombre\":\"Vaqueros Tipo Boyfriend Con Cremallera Con Bolsillo Con Solapa\",\"categoria\":\"Futbol\",\"precio\":103506,\"enstock\":false,\"imagen\":\"https://img.ltwebstatic.com/images3_pi/2021/11/02/1635841082d1c54695c1e0cfdc4533f2f6965f5bb1_thumbnail_750x.webp\"},{\"nombre\":\"Abrigo de cuello con solapa con diseño con solapa\",\"categoria\":\"Futbol\",\"precio\":109430,\"enstock\":true,\"imagen\":\"https://img.ltwebstatic.com/images3_pi/2021/08/23/16296754474d41df47b4c1612bdc5404c8598cb039_thumbnail_600x.webp\"},{\"nombre\":\"Blusa floral de espalda con cobertura de ojo de puño con nudo\",\"categoria\":\"Futbol\",\"precio\":50706,\"enstock\":true,\"imagen\":\"https://img.ltwebstatic.com/images3_pi/2021/06/07/1623062053172aa76622f89931ddd70787853c5ce2_thumbnail_600x.webp\"},{\"nombre\":\"Abrigo De Cuadros Sin Camiseta\",\"categoria\":\"Futbol\",\"precio\":119430,\"enstock\":true,\"imagen\":\"https://img.ltwebstatic.com/images3_pi/2021/09/10/1631255055f00ca749f5dcc1f5cef12c19a983de5c_thumbnail_750x.webp\"}, {\"nombre\":\"Camisa manga larga para hombre estampado botanico\",\"categoria\":\"Futbol\",\"precio\":94950,\"enstock\":true,\"imagen\":\"https://cuerosvelezco.vteximg.com.br/arquivos/ids/216462-1563-2344/1030747-10-02-camisa.jpg?v=637596467890430000\"}, {\"nombre\":\"Pantalon depor hombre\",\"categoria\":\"Futbol\",\"precio\":60000,\"enstock\":true,\"imagen\":\"https://falabella.scene7.com/is/image/FalabellaCO/9139324_1?wid=800&hei=800&qlt=70\"}, {\"nombre\":\"Camiseta depor hombre\",\"categoria\":\"Futbol\",\"precio\":89900,\"enstock\":true,\"imagen\":\"https://falabella.scene7.com/is/image/FalabellaCO/8259164_1?wid=1500&hei=1500&qlt=70\"}, {\"nombre\":\"Maletin depor \",\"categoria\":\"Futbol\",\"precio\":99000,\"enstock\":true,\"imagen\":\"https://falabella.scene7.com/is/image/FalabellaPE/16745713_1?wid=800&hei=800&qlt=70\"}, {\"nombre\":\"Mochila con solapa con estampado geométrico \",\"categoria\":\"Futbol\",\"precio\":19451,\"enstock\":true,\"imagen\":\"https://img.ltwebstatic.com/images3_pi/2021/08/16/162907959091628aa85c08066058a1234eafe6cf15_thumbnail_600x.webp\"}, {\"nombre\":\"Prendedor encanto silueta jaguar metalico dorado\",\"categoria\":\"Futbol\",\"precio\":49900,\"enstock\":true,\"imagen\":\"https://cuerosvelezco.vteximg.com.br/arquivos/ids/224479-1563-2344/1032393-04-01-Prendedor.jpg?v=637714657132930000\"}, {\"nombre\":\"Gorra Baker Unicolor \",\"categoria\":\"Futbol\",\"precio\":19905,\"enstock\":true,\"imagen\":\"https://img.ltwebstatic.com/images3_pi/2021/10/29/16354939826c7860c6ed9e516b71ffa33769e8b196_thumbnail_750x.webp\"}, {\"nombre\":\"Botas Minimalista Con Cordón Delantero De Felpa Interior\",\"categoria\":\"Futbol\",\"precio\":183126,\"enstock\":true,\"imagen\":\"https://img.ltwebstatic.com/images3_pi/2021/10/19/163462562374a027b278194c5366e6bbfd8846e746_thumbnail_750x.webp\"}, {\"nombre\":\"Tenis suela tractor capellada en combina\",\"categoria\":\"Futbol\",\"precio\":179900,\"enstock\":true,\"imagen\":\"https://elaco.vteximg.com.br/arquivos/ids/526158-1000-1464/-elaco-producto-Tenis-MAUVE-e351507-1.jpg?v=637655175845630000\"}, {\"nombre\":\"Zapatos cordon ground de cuero\",\"categoria\":\"Futbol\",\"precio\":309900,\"enstock\":true,\"imagen\":\"https://cuerosvelezco.vteximg.com.br/arquivos/ids/223501-1563-2344/1031856-02-04-Tenis-de-cuero.jpg?v=637699147519770000\"}, {\"nombre\":\"Botas Cuñas Con Diseño De Hebilla Con Cordón\",\"categoria\":\"Futbol\",\"precio\":195069,\"enstock\":true,\"imagen\":\"https://img.ltwebstatic.com/images3_pi/2021/08/09/1628508655d9ff70d98969758d17f09938569d4caf_thumbnail_750x.webp\"}]";


    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        txt_galeria = root.findViewById(R.id.txt_galeria);
        txt_galeria.setText("hola mundo");

        spn_categorias= root.findViewById(R.id.spn_categorias);

        String[] categorias = new String[]{"Ropa Mujer", "Ropa Hombre","Accesorios Mujer", "Accesorios Hombre", "Zapatos Mujer", "Zapatos Hombre"};

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
            //String categoria = this.productos.getJSONObject(position).getString("categoria");
            int precio = this.productos.getJSONObject(position).getInt("precio");
            String imagen = this.productos.getJSONObject(position).getString("imagen");

            holder.tev_item_name.setText(nombre);
            //holder.tev_item_categoria.setText(categoria);
            holder.tev_item_precio.setText("$" + precio);

            Glide.with(miActividad).load(imagen).into(holder.imv_producto);

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
        //private TextView tev_item_categoria;
        private TextView tev_item_precio;
        private ImageView imv_producto;
        public ViewHolder(View v) {
            super(v);
            tev_item_name = v.findViewById(R.id.tev_item_name);
           // tev_item_categoria = v.findViewById(R.id.tev_item_categoria);
            tev_item_precio = v.findViewById(R.id.tev_item_precio);
            imv_producto = v.findViewById(R.id.imv_producto);

        }
    }
}