package com.unicaldasmafe.example.grupo10;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.font.TextAttribute;

public class DetalleProductoActivity extends AppCompatActivity {

    private ImageView imv_detalle_imagen;
    private TextView tev_detalle_nombre;
    private TextView tev_detalle_categoria;
    private TextView tev_detalle_precio;
    private TextView tev_detalle_stock;
    private TextView tev_detalle_especificaciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        imv_detalle_imagen = findViewById(R.id.imv_detalle_imagen);
        tev_detalle_nombre = findViewById(R.id.tev_detalle_nombre);
        tev_detalle_categoria = findViewById(R.id.tev_detalle_categoria);
        tev_detalle_precio = findViewById(R.id.tev_detalle_precio);
        tev_detalle_stock = findViewById(R.id.tev_detalle_stock);
        tev_detalle_especificaciones = findViewById(R.id.tev_detalle_especificaciones);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Log.e("PRODUCTO_RECIBIDO", bundle.getString("producto"));

            try {
                JSONObject producto = new JSONObject(bundle.getString("producto"));

                String nombre = producto.getString("nombre");
                String categoria = producto.getString("categoria");
                String precio = producto.getString("precio");
                boolean enstock = producto.getBoolean("enstock");
                String imagen = producto.getString("url");
                String detalles=producto.getString("detalles");

                tev_detalle_nombre.setText(nombre);
                tev_detalle_categoria.setText(categoria);
                tev_detalle_precio.setText("$" + precio);
                tev_detalle_especificaciones.setText(detalles);

                if (enstock) {
                    tev_detalle_stock.setText("Producto disponible");
                    tev_detalle_stock.setTextColor(Color.GREEN);
                } else {
                    tev_detalle_stock.setText("Producto agotado");
                    tev_detalle_stock.setTextColor(Color.RED);
                }

                Glide.with(this)
                        .load(imagen)
                        .placeholder(new ColorDrawable(Color.BLACK))
                        .into(imv_detalle_imagen);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}