package com.backend.ContactListApi.Services;

import com.backend.ContactListApi.Dtos.ContactDTO;
import com.backend.ContactListApi.Entities.Contact;
import com.backend.ContactListApi.Exceptions.ResourceNotFoundException;
import com.backend.ContactListApi.Repositories.ContactRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@AllArgsConstructor
@Service
public class ContactService implements ContactRepository{

    @Autowired
    private ContactRepository contactRepository;
    //dependencia injectable
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public <S extends Contact> S save(S entity) {
        return contactRepository.save(entity);
    }
    public Contact create(ContactDTO contactDTO){
        //transferir valores 1.argu fuente 2.argu destino
        Contact contact = mapper.map(contactDTO, Contact.class);
        //Asignarle la hor de registro al contacto
        contact.setCreatedAt(LocalDateTime.now());
        return save(contact);
    }

    public Contact update(Long id, ContactDTO contactDTO){
        Contact contactFormOb = findById(id).orElseThrow(ResourceNotFoundException::new);
//        //instancia de ModelMapper
//        ModelMapper maper = new ModelMapper();
        //transferir valores 1.argu fuente 2.argu destino
        mapper.map(contactDTO, contactFormOb);
        return save(contactFormOb);
    }

    @Override
    public Optional<Contact> findById(Long aLong) {
        return contactRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {

        return contactRepository.existsById(aLong);
    }


    @Override
    public <S extends Contact> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        contactRepository.deleteById(aLong);
    }

    public Contact updteById(Long aLong, Contact contact){
        return null;
    }

    @Override
    public void delete(Contact entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Contact> entities) {

    }

    @Override
    public void deleteAll() {

    }


    @Override
    public void flush() {

    }

    @Override
    public <S extends Contact> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Contact> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Contact> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Contact getOne(Long aLong) {
        return null;
    }

    @Override
    public Contact getById(Long aLong) {
        return null;
    }

    @Override
    public Contact getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Contact> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Contact> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Contact> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Contact> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Contact> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Contact> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Contact, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Contact> findAll(Sort sort) {
        return contactRepository.findAll(sort);
    }

    @Override
    public Page<Contact> findAll(Pageable pageable) {
        return null;
    }
}
