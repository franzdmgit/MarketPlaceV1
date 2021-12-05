package com.unicaldasmafe.example.grupo10.ui.gallery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.unicaldasmafe.example.grupo10.DetalleProductoActivity;
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


    private String productos=  "[{\"nombre\":\"Blusa floral de espalda con abertura de ojo de puño con nudo\",\"categoria\":\"Ropa Mujer\",\"precio\":50807,\"enstock\":true,\"url\":\"https://img.ltwebstatic.com/images3_pi/2021/06/07/1623062053172aa76622f89931ddd70787853c5ce2_thumbnail_600x.webp\",\"sucursal\":[{\"nombre\":\"Sucursal A\",\"direccion\":\"Diección A\",\"encargado\":{\"nombre\":\"Encargado A\"}},{\"nombre\":\"Sucursal B\",\"direccion\":\"Diección B\",\"encargado\":{\"nombre\":\"Encargado B\"}}]},{\"nombre\":\"Abrigo de cuello con solapa con diseño de solapa\",\"categoria\":\"Ropa Mujer\",\"precio\":109430,\"enstock\":true,\"url\":\"https://img.ltwebstatic.com/images3_pi/2021/08/23/16296754474d41df47b4c1612bdc5404c8598cb039_thumbnail_600x.webp\",\"sucursal\":[{\"nombre\":\"Sucursal C\",\"direccion\":\"Diección C\",\"encargado\":{\"nombre\":\"Encargado C\"}},{\"nombre\":\"Sucursal D\",\"direccion\":\"Diección D\",\"encargado\":{\"nombre\":\"Encargado D\"}}]},{\"nombre\":\"Camisa flok belmira para mujer manga larga\",\"categoria\":\"Ropa Mujer\",\"precio\":189900,\"enstock\":true,\"url\":\"https://cuerosvelezco.vteximg.com.br/arquivos/ids/222513-1563-2344/1032041-31-02-Camisa-manga-larga.jpg?v=637692173974200000\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Pantalones unicolor de pierna amplia\",\"categoria\":\"Ropa Mujer\",\"precio\":62532,\"enstock\":false,\"url\":\"https://img.ltwebstatic.com/images3_pi/2020/12/30/1609296900ff5f038017ec77fb7f69f9df65d6dce6_thumbnail_600x.webp\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Camisa hombre margalarga\",\"categoria\":\"Ropa Hombre\",\"precio\":69900,\"enstock\":false,\"url\":\"https://falabella.scene7.com/is/image/FalabellaCO/881900634_1?wid=1500&hei=1500&qlt=70\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Abrigo De Cuadros Sin Camiseta\",\"categoria\":\"Ropa Hombre\",\"precio\":119430,\"enstock\":true,\"url\":\"https://img.ltwebstatic.com/images3_pi/2021/09/10/1631255055f00ca749f5dcc1f5cef12c19a983de5c_thumbnail_750x.webp\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Pantalones Cargo Con Bolsillo Con Solapa\",\"categoria\":\"Ropa Hombre\",\"precio\":119430,\"enstock\":true,\"url\":\"https://img.ltwebstatic.com/images3_pi/2021/10/27/1635300473a23eb1e0eceda92ac16b0dbe7590722c_thumbnail_750x.webp\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"pantalon deportivo hombre\",\"categoria\":\"Ropa Hombre\",\"precio\":60000,\"enstock\":true,\"url\":\"https://falabella.scene7.com/is/image/FalabellaCO/9139324_1?wid=800&hei=800&qlt=70\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Maletin deportiva\",\"categoria\":\"Accesorios\",\"precio\":99000,\"enstock\":true,\"url\":\"https://falabella.scene7.com/is/image/FalabellaPE/16745713_1?wid=800&hei=800&qlt=70\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Relojes de hombre Blanco Casual\",\"categoria\":\"Accesorios\",\"precio\":15633,\"enstock\":true,\"url\":\"https://img.ltwebstatic.com/images3_pi/2021/05/14/16209793800bef7731e5fddc4ab0341a21bb7e6b32_thumbnail_600x.webp\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Mochila con solapa con estampado geométrico\",\"categoria\":\"Accesorios\",\"precio\":19541,\"enstock\":true,\"url\":\"https://img.ltwebstatic.com/images3_pi/2021/08/16/162907959091628aa85c08066058a1234eafe6cf15_thumbnail_600x.webp\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Prendedor encanto silueta jaguar metálico dorado\",\"categoria\":\"Accesorios\",\"precio\":49900,\"enstock\":true,\"url\":\"https://cuerosvelezco.vteximg.com.br/arquivos/ids/224479-1563-2344/1032393-04-01-Prendedor.jpg?v=637714657132930000\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Prendedor encanto silueta jaguar metálico dorado\",\"categoria\":\"Zapatos\",\"precio\":49900,\"enstock\":true,\"url\":\"https://cuerosvelezco.vteximg.com.br/arquivos/ids/224479-1563-2344/1032393-04-01-Prendedor.jpg?v=637714657132930000\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Zapatos cordón ground de cuero para hombre inspiración deportiva\",\"categoria\":\"Zapatos\",\"precio\":309900,\"enstock\":true,\"url\":\"https://cuerosvelezco.vteximg.com.br/arquivos/ids/223501-1563-2344/1031856-02-04-Tenis-de-cuero.jpg?v=637699147519770000\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Tenis suela tractor capellada en combina\",\"categoria\":\"Zapatos\",\"precio\":179900,\"enstock\":true,\"url\":\"https://elaco.vteximg.com.br/arquivos/ids/526158-1000-1464/-elaco-producto-Tenis-MAUVE-e351507-1.jpg?v=637655175845630000\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"Botas Minimalista Con Cordón Delantero De Felpa Interior\",\"categoria\":\"Zapatos\",\"precio\":183126,\"enstock\":true,\"url\":\"https://img.ltwebstatic.com/images3_pi/2021/10/19/163462562374a027b278194c5366e6bbfd8846e746_thumbnail_750x.webp\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]}]";



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
            String imagen =this.productos.getJSONObject(position).getString("url");

            holder.tev_item_name.setText(nombre);
            holder.tev_item_categoria.setText(categoria);
            holder.tev_item_precio.setText("$" + precio);

            Glide.with(miActividad).load(imagen).placeholder(new ColorDrawable(Color.BLACK)).into(holder.imv_producto);
            holder.imv_producto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        //Log.e("PRODUCTOS", productos.getJSONObject(position).toString());
                        Intent intent = new Intent(miActividad, DetalleProductoActivity.class);

                        intent.putExtra("producto", productos.getJSONObject(position).toString());

                        miActividad.startActivity(intent);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        catch (JSONException e) {
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
        private ImageView imv_producto;
        public ViewHolder(View v) {
            super(v);
            tev_item_name = v.findViewById(R.id.tev_item_name);
            tev_item_categoria = v.findViewById(R.id.tev_item_categoria);
            tev_item_precio = v.findViewById(R.id.tev_item_precio);
            imv_producto=v.findViewById(R.id.imv_producto);

        }
    }
}