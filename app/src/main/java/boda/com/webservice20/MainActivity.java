package boda.com.webservice20;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import boda.com.mylibrary.Asynchtask;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    private EditText edt1, edt2, edt3, edt4;
    private Button   btnGuardar, btnActualizar, btnEliminar, btnBuscar;

    /*--------------------------------------------------------------------------------------------*/
    final String NAMESPACE = "http://wservice/";
    final String URL="http://armando.j.facilcloud.com/procesoWebService?wsdl";

    final String METHOD_SAVE = "save";
    final String SOAP_ACTION_SAVE = "http://wservice/save";

    final String METHOD_UPDATE = "update";
    final String SOAP_ACTION_UPDATE = "http://wservice/update";

    final String METHOD_DELETE = "delete";
    final String SOAP_ACTION_DELETE = "http://wservice/delete";

    final String METHOD_SEARCH = "searchUser";
    final String SOAP_ACTION_SEARCH = "http://wservice/searchUser";

    final String METHOD_MOSTRARNOMBRE = "mostrarNombre";
    final String SOAP_ACTION_MOSTRARNOMBRE = "http://wservice/mostrarNombre";

    final String METHOD_MOSTRARAPE = "mostrarApellidos";
    final String SOAP_ACTION_MOSTRARAPE = "http://wservice/mostrarApellidos";

    final String METHOD_MOSTRARMAIL = "mostrarCorreo";
    final String SOAP_ACTION_MOSTRARMAIL = "http://wservice/mostrarCorreo";

    /*--------------------------------------------------------------------------------------------*/
    private String resultado = "";
    private Insertar   tar = null;
    private Actualizar zar = null;
    private Eliminar   nar = null;
    private Buscar     car = null;

    /*--------------------------------------------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1 = (EditText) findViewById(R.id.edtNombre);
        edt2 = (EditText) findViewById(R.id.edtApellidos);
        edt3 = (EditText) findViewById(R.id.edtCorreo);
        edt4 = (EditText) findViewById(R.id.edtBuscar);

        btnGuardar = (Button) findViewById(R.id.btnSave);
        btnActualizar = (Button) findViewById(R.id.btnUpdate);
        btnEliminar = (Button) findViewById(R.id.btnDelete);
        btnBuscar = (Button) findViewById(R.id.btnSearch);

    } //termina metodo onCreate

    /*--------------------------------------------------------------------------------------------*/
    private class Insertar extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            //WebService - Opciones
            SoapObject request = new SoapObject(NAMESPACE, METHOD_SAVE);
            request.addProperty("arg0", edt1.getText().toString());
            request.addProperty("arg1", edt2.getText().toString());
            request.addProperty("arg2", edt3.getText().toString());

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);

            HttpTransportSE ht = new HttpTransportSE(URL);
            try {
                ht.call(SOAP_ACTION_SAVE, envelope);
                SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
                resultado=response.toString();
                if(resultado.equals("1"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    } //termina metodo Insertar

    /*--------------------------------------------------------------------------------------------*/
    private class Actualizar extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            //WebService - Opciones
            SoapObject request = new SoapObject(NAMESPACE, METHOD_UPDATE);
            request.addProperty("arg1", edt1.getText().toString());
            request.addProperty("arg2", edt2.getText().toString());
            request.addProperty("arg3", edt3.getText().toString());
            request.addProperty("arg0", edt4.getText().toString());

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);

            HttpTransportSE ht = new HttpTransportSE(URL);
            try {
                ht.call(SOAP_ACTION_UPDATE, envelope);
                SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
                resultado=response.toString();
                if(resultado.equals("1"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    } //termina metodo Actualizar

    /*--------------------------------------------------------------------------------------------*/
    private class Eliminar extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            //WebService - Opciones
            SoapObject request = new SoapObject(NAMESPACE, METHOD_DELETE);
            request.addProperty("arg0", edt4.getText().toString());

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);

            HttpTransportSE ht = new HttpTransportSE(URL);
            try {
                ht.call(SOAP_ACTION_DELETE, envelope);
                SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
                resultado=response.toString();
                if(resultado.equals("1"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return true;
        }
    }  //termina método Eliminar
	
	/*--------------------------------------------------------------------------------------------*/
    public void mostrarUsuario() {
        SoapObject request = new SoapObject(NAMESPACE, METHOD_SEARCH);
        request.addProperty("arg0", edt4.getText().toString());

        SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE ht = new HttpTransportSE(URL);
        try {
            ht.call(SOAP_ACTION_SEARCH, envelope);
            SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
            resultado=response.toString();
            if(resultado.equals("1"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    } //termina metodo mostrarUsuario

    SoapPrimitive nombre;
    public void mostrarNombre() {
        SoapObject request = new SoapObject(NAMESPACE, METHOD_MOSTRARNOMBRE);
        request.addProperty("arg0", edt4.getText().toString());

        SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE ht = new HttpTransportSE(URL);
        try {
            ht.call(SOAP_ACTION_MOSTRARNOMBRE, envelope);
            nombre = (SoapPrimitive)envelope.getResponse();
            resultado=nombre.toString();
            if(resultado.equals("1"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    } //termina metodo MostrarNombre

    SoapPrimitive apell;
    public void mostrarApellidos() {
        SoapObject request = new SoapObject(NAMESPACE, METHOD_MOSTRARAPE);
        request.addProperty("arg0", edt4.getText().toString());

        SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE ht = new HttpTransportSE(URL);
        try {
            ht.call(SOAP_ACTION_MOSTRARAPE, envelope);
            apell = (SoapPrimitive)envelope.getResponse();
            resultado=apell.toString();
            if(resultado.equals("1"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    } //termina metodo mostrarApellidos

    SoapPrimitive correo;
    public void mostrarCorreo() {
        SoapObject request = new SoapObject(NAMESPACE, METHOD_MOSTRARMAIL);
        request.addProperty("arg0", edt4.getText().toString());

        SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE ht = new HttpTransportSE(URL);
        try {
            ht.call(SOAP_ACTION_MOSTRARMAIL, envelope);
            correo = (SoapPrimitive)envelope.getResponse();
            resultado=correo.toString();
            if(resultado.equals("1"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    } //termina metodo mostrarCorreo

    /*--------------------------------------------------------------------------------------------*/
    private class Buscar extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            mostrarUsuario();
            mostrarNombre();
            mostrarApellidos();
            mostrarCorreo();
            return  null;
        }

        @Override
        protected void onPostExecute(Void arams) {
            edt1.setText(nombre.toString());
            edt2.setText(apell.toString());
            edt3.setText(correo.toString());
        }
    }//termina metodo Buscar

    /*--------------------------------------------------------------------------------------------*/
    public void actions(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                tar = new Insertar();
                tar.execute();
                Toast toast1 = Toast.makeText(getApplicationContext(), R.string.msj1, Toast.LENGTH_SHORT);
                toast1.show();
                limpiar();
                break;

            case R.id.btnUpdate:
                zar = new Actualizar();
                zar.execute();
                Toast toast2 = Toast.makeText(getApplicationContext(), R.string.msj2, Toast.LENGTH_SHORT);
                toast2.show();
                limpiar();
                break;
            case R.id.btnDelete:
                nar = new Eliminar();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setMessage(R.string.msj5);
                alertDialog.setTitle(R.string.tituloMensajeAdvertencia);
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton(R.string.confirm1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nar.execute();
                        Toast toast3 = Toast.makeText(getApplicationContext(), R.string.msj3, Toast.LENGTH_SHORT);
                        toast3.show();
                        limpiar();
                    }
                });
                alertDialog.setNegativeButton(R.string.confirm2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edt4.setText("");
                    }
                });
                alertDialog.show();

                break;
            case R.id.btnSearch:
                car = new Buscar();
                car.execute();
                Toast toast4 = Toast.makeText(getApplicationContext(), R.string.msj4, Toast.LENGTH_SHORT);
                toast4.show();
                break;

            default:
                break;
        } //termina switch
    } //termina v.getId

    /*--------------------------------------------------------------------------------------------*/
    @Override
    public void processFinish(String result) {
        Log.i("processFinish", result);
    }

    public void limpiar() {
        edt1.setText("");
        edt2.setText("");
        edt3.setText("");
        edt4.setText("");
    }
} //termina MainActivity
