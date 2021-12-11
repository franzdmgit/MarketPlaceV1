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
import android.widget.Button;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
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

    private String productos ="[{\"nombre\":\"CAMISETA KAYSERI VELVET PARA MUJER MANGA 3/4\",\"categoria\":\"Ropa Mujer\",\"precio\":74950,\"enstock\":true,\"imagen\":\"https://cuerosvelezco.vteximg.com.br/arquivos/ids/221281-1563-2344/1031380-07-02-Camiseta-velvet.jpg?v=637673108348930000\",\"detalle\":\"Camiseta oversized elaborada en tela velvet con brillo sutil, diseño de cuello semi bandeja, manga 3/4 integrada y largo a la cintura baja. Las formas simples son exaltadas a través de las telas usadas en esta camiseta para mujer, cuyos efectos de brillo-mate y tacto súper suaves le imprimen un aire sofisticado a tus looks de uso cotidiano.\",\"sucursal\":[{\"nombre\":\"Sucursal A\",\"direccion\":\"Diección A\",\"encargado\":{\"nombre\":\"Encargado A\"}},{\"nombre\":\"Sucursal B\",\"direccion\":\"Diección B\",\"encargado\":{\"nombre\":\"Encargado B\"}}]},{\"nombre\":\"POLO FRAILEJÓN TERRACOTA PARA HOMBRE ASPECTO MELANGE\",\"categoria\":\"Ropa Hombre\",\"precio\":170000,\"enstock\":false,\"imagen\":\"https://cuerosvelezco.vteximg.com.br/arquivos/ids/225614-1563-2344/1032027-02-04-Polo.jpg?v=637739865640100000\",\"detalle\":\"Camisa tipo polo manga corta y corte semifit; confeccionada en una base de hilos color terrosa para destacar la apariencia premium característica del ADN Vélez para tus looks casuales e informales. Además, tiene aberturas laterales con su panel frontal mas corto que el posterior. Esta camisa para hombre la puedes llevar con jeans, tenis o ataduras y es ideal para el uso cotidiano.\",\"sucursal\":[{\"nombre\":\"Sucursal C\",\"direccion\":\"Diección C\",\"encargado\":{\"nombre\":\"Encargado C\"}},{\"nombre\":\"Sucursal D\",\"direccion\":\"Diección D\",\"encargado\":{\"nombre\":\"Encargado D\"}}]},{\"nombre\":\"BILLETERA PORTA CHEQUERA ENCANTO DE CUERO PARA MUJER BORDADO FLORAL MIEL\",\"categoria\":\"Accesorios\",\"precio\":300000,\"enstock\":true,\"imagen\":\"https://cuerosvelezco.vteximg.com.br/arquivos/ids/224115-1563-2344/1031977-02-01-Porta-chequera-de-cuero.jpg?v=637707680346800000\",\"detalle\":\"Nuestra porta chequera de amplia capacidad, está elaborada en cuero de aspecto y brillo natural. Su diseño es un reflejo de la última película de Disney, Encanto, que rinde un homenaje a la biodiversidad colombiana. Su monedero interno y su sistema de cierre con cremallera, hacen de esta una pieza ideal para que complementes tus looks casuales. Cuenta con tecnología RFID que garantiza la seguridad de tus datos y evita la clonación de tus tarjetas, además de una cargadera ajustable que amarás.\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"TENIS PLANOS DE CUERO PARA MUJER SELVÁTICA LIBRE COLECCIÓN ENCANTO\",\"categoria\":\"Zapatos\",\"precio\":390000,\"enstock\":true,\"imagen\":\"https://cuerosvelezco.vteximg.com.br/arquivos/ids/224078-1563-2344/1031908-09-02-Tenis-de-cuero.jpg?v=637707636127630000\",\"detalle\":\"Tenis para mujer selvática libre: el aire fresco de los bosques, el sonido de las aves y la magia de los colores que dan vida a los manglares colombianos son un homenaje a los seres libres que se ve representado en esta pieza de lujo artesanal y lleva el sello de una marca de tradición como Vélez. Estos tenis que cuentan con una suela liviana y plantilla con confort, generan un punto de encuentro entre la comodidad y el buen gusto que complementan diariamente tus looks de estilo bohemio.\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"KIT BILLETERA Y LLAVERO DE CUERO VINTAGE BRANDY\",\"categoria\":\"Accesorios\",\"precio\":200000,\"enstock\":true,\"imagen\":\"https://cuerosvelezco.vteximg.com.br/arquivos/ids/220736-1563-2344/1022111-05-01-Kit-billetera-y-llavero-de-cuero.jpg?v=637671458932830000\",\"detalle\":\"Un kit de infaltables para uso cotidiano! Nuestra billetera de cuero con dos cuerpos y bolsillo flotante ofrece funcionalidad en múltiples servicios internos y larga duración en nuestro cuero graso y rústico, de aspecto natural. La mezcla de cuero y zamac son un distintivo de lujo para portar tus llaves de forma segura. Los herrajes de acabado envejecido se ajustan a tu estilo casual.\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]},{\"nombre\":\"BOTAS CASUAL DE CUERO VINTAGE MIEL 42\",\"categoria\":\"Zapatos\",\"precio\":430000,\"enstock\":true,\"imagen\":\"https://cuerosvelezco.vteximg.com.br/arquivos/ids/217413-1563-2344/1022614-02-02-botas-de-cuero.jpg?v=637624913025330000\",\"detalle\":\"Nuestras botas en cuero de inspiración militar ofrecen un look casual outdoor para tu personalidad aguerrida!\",\"sucursal\":[{\"nombre\":\"Sucursal E\",\"direccion\":\"Diección E\",\"encargado\":{\"nombre\":\"Encargado E\"}},{\"nombre\":\"Sucursal F\",\"direccion\":\"Diección F\",\"encargado\":{\"nombre\":\"Encargado F\"}}]}]";




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        txt_galeria = root.findViewById(R.id.txt_galeria);
        spn_categorias = root.findViewById(R.id.spn_categorias);

        String[] categorias = new String[]{"Ropa Mujer", "Ropa Hombre", "Accesorios", "Zapatos"};

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

        //tev_galeria.setText("Grupo 10");

        rev_productos = root.findViewById(R.id.rev_productos);
        rev_productos.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rev_productos.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        try {
            JSONArray jsonProductos = new JSONArray(productos);

            //mAdapter = new ProductosAdapter(jsonProductos, getActivity());

            //rev_productos.setAdapter(mAdapter);


            JSONObject producto0 = jsonProductos.getJSONObject(0);

            String nombre = producto0.getString("nombre");

            JSONArray sucursal = producto0.getJSONArray("sucursal");

            JSONObject sucursal1 = sucursal.getJSONObject(1);

            String nombreSucursal = sucursal1.getString("nombre");

            Toast.makeText(getActivity(), "NOMBRE: "+ nombreSucursal, Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("productos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            JSONArray productos = new JSONArray();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.e("TAG", document.getId() + " => " + document.getData());

                                String nombre = document.getData().get("nombre").toString();
                                String categoria = document.getData().get("categoria").toString();
                                int precio = Integer.parseInt(document.getData().get("precio").toString());
                                boolean enstock = Boolean.parseBoolean(document.getData().get("enstock").toString());
                                String imagen = document.getData().get("imagen").toString();



                                JSONObject producto = new JSONObject();
                                try {
                                    producto.put("nombre", nombre);
                                    producto.put("categoria", categoria);
                                    producto.put("precio", precio);
                                    producto.put("enstock", enstock);
                                    producto.put("imagen", imagen);




                                    productos.put(producto);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }

                            mAdapter = new ProductosAdapter(productos, getActivity());

                            rev_productos.setAdapter(mAdapter);


                        } else {
                            Log.e("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });


        return root;
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
            String nombre = productos.getJSONObject(position).getString("nombre");
            String categoria = productos.getJSONObject(position).getString("categoria");
            int precio = productos.getJSONObject(position).getInt("precio");
            String imagen = productos.getJSONObject(position).getString("imagen");



            holder.tev_item_name.setText(nombre);
            holder.tev_item_categoria.setText(categoria);
            holder.tev_item_precio.setText("$" + precio);




            if (imagen.equals("picasso")) {

                holder.imv_producto.setImageResource(miActividad.getResources().getIdentifier(imagen, "drawable", miActividad.getPackageName()));

                //holder.imv_item_producto.setImageDrawable(miActividad.getDrawable(R.drawable.picasso));
            } else {
                Glide.with(miActividad)
                        .load(imagen)
                        .placeholder(new ColorDrawable(Color.BLACK))
                        .into(holder.imv_producto);
            }

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


            holder.btn_item_favorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Log.e("PRODUCTO FAVORITO", productos.getJSONObject(position).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


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
        private ImageView imv_producto;
        private Button btn_item_favorito;


        public ViewHolder(View v) {
            super(v);
            tev_item_name = v.findViewById(R.id.tev_item_name);
            tev_item_categoria = v.findViewById(R.id.tev_item_categoria);
            tev_item_precio = v.findViewById(R.id.tev_item_precio);
            imv_producto = v.findViewById(R.id.imv_producto);
            btn_item_favorito = v.findViewById(R.id.btn_item_favorito);



        }
    }
}