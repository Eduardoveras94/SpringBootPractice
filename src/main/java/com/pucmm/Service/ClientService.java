/**
 * Created by Djidjelly Siclait on 10/5/2016.
 */
package com.pucmm.Service;

import com.pucmm.Entiy.Client;
import com.pucmm.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Core Functions
    public void registerNewClient(String identificationNumber, String firstName, String lastName, String telephone, String address){
        clientRepository.save(new Client(identificationNumber, firstName, lastName, telephone, address));
    }

    public Client findClient(String identificationNumber){
        return clientRepository.findByIdentificationNumber(identificationNumber);
    }

    public List<Client> findAllClients(){
        return clientRepository.findAll();
    }
}