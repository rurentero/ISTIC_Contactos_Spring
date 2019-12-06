package io.swagger.api;
import java.io.DataOutputStream;
import java.net.Socket;

import io.swagger.model.Contacto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.Ubicacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-08T18:01:04.874Z[GMT]")
@Controller
public class ContactosApiController implements ContactosApi {

    private static final Logger log = LoggerFactory.getLogger(ContactosApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    //Mocked contact list
    private List<Contacto> agenda = new LinkedList<Contacto>();

    @org.springframework.beans.factory.annotation.Autowired
    public ContactosApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addContact(@ApiParam(value = "Contacto que se va a añadir a la lista" ,required=true )  @Valid @RequestBody Contacto body) {
        if (agenda.contains(body)) {
            return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
        }else {
            agenda.add(body);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Void> deleteContact(@ApiParam(value = "",required=true) @PathVariable("contactId") Long contactId) {
        boolean deleted = false;
        for (Contacto c : agenda) {
            if(c.getId().equals(contactId)) {
                agenda.remove(c);
                deleted=true;
            }
        }
        if (deleted)
            return new ResponseEntity<Void>(HttpStatus.OK);
        else
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Contacto> findClosest() {
        final long t_start = System.currentTimeMillis();

        // Mock implementation
        final Contacto contacto = new Contacto();
        contacto.setId(Long.valueOf(1));
        contacto.setName("Francisco");
        contacto.setNumero("927112233");
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setLatitud(BigDecimal.valueOf(39.479483));
        ubicacion.setLongitud(BigDecimal.valueOf(-6.342079));
        contacto.setUbicacion(ubicacion);
        log.info("Retornando contacto cercano");
        final long t_end = System.currentTimeMillis();

        //Creación de un nuevo thread para que la métrica no interfiera en el tiempo de respuesta del endpoint.
        new Thread() {
            public void run() {
                try { //Envío de métricas
                    // Para testear distintos valores de las métricas, se provocarán status 500, 404 y 200 de forma aleatoria.
                    switch (new Random().nextInt(10)) {
                        // Registrar un 500 (2/10)
                        case 0:
                        case 1:
                            Socket conn1 = new Socket("39711718.carbon.hostedgraphite.com", 2003);
                            DataOutputStream dos1 = new DataOutputStream(conn1.getOutputStream());
                            dos1.writeBytes("edff5a27-c65f-443e-8e69-88ff29994b57.contactos.buscarCercano.500 "+1+"\n");
                            conn1.close();
                            log.info("Metrica: 500");
                            break;
                        // Registrar un 404 (2/10)
                        case 2:
                        case 3:
                            Socket conn2 = new Socket("39711718.carbon.hostedgraphite.com", 2003);
                            DataOutputStream dos2 = new DataOutputStream(conn2.getOutputStream());
                            dos2.writeBytes("edff5a27-c65f-443e-8e69-88ff29994b57.contactos.buscarCercano.404 "+1+"\n");
                            conn2.close();
                            log.info("Metrica: 404");
                            break;
                        // Registrar exito (métrica de tiempo de ejecucion) (6/10)
                        default:
                            long t_execution =  (t_end - t_start);
                            log.info("Metrica: Tiempo de ejecucion " + t_execution);
                            Socket conn = new Socket("39711718.carbon.hostedgraphite.com", 2003);
                            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                            dos.writeBytes("edff5a27-c65f-443e-8e69-88ff29994b57.contactos.buscarCercano.tiempoEjecucion "+t_execution+1+"\n"); //+1 ms
                            conn.close();
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        return new ResponseEntity<Contacto>(contacto, HttpStatus.OK);
    }

    public ResponseEntity<List<Contacto>> getContacts() {
        return new ResponseEntity<List<Contacto>>(agenda, HttpStatus.OK);
    }

}
