package com.fral.extreme.s4.domain.repository;

import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.domain.model.Student;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.*;

@Repository
public class S4SystemMockDao implements S4SystemDao {

    private static long STUDENTS_ID_TRACKER = 0;
    private static long CLASSES_ID_TRACKER = 0;

    private List<Class> classesCollection;
    private List<Student> studentsCollection;

    public S4SystemMockDao() {
        classesCollection = new ArrayList<>();
        studentsCollection = new ArrayList<>();
    }

    @Override
    public <T> T persist(T entity) {

        if (entity.getClass().equals(Class.class)) {
            Class entityToAdd = Class.class.cast(entity);

            int position = classesCollection.indexOf(entityToAdd);

            if (position != -1) {
                classesCollection.set(position, entityToAdd);
            } else {
                entityToAdd.setId(CLASSES_ID_TRACKER);
                classesCollection.add(entityToAdd);
                CLASSES_ID_TRACKER += 1;
            }

            return entity;
        } else if (entity.getClass().equals(Student.class)) {
            Student studentToAdd = Student.class.cast(entity);
            int position = studentsCollection.indexOf(studentToAdd);

            if (position != -1) {
                studentsCollection.set(position, studentToAdd);
            } else {
                studentToAdd.setId(STUDENTS_ID_TRACKER);
                studentsCollection.add(studentToAdd);
                STUDENTS_ID_TRACKER += 1;
            }

            return entity;
        }

        return null;
    }

    @Override
    public <T> Collection<T> persist(T[] entities) {
        throw new NotYetImplementedException();
    }

    @Override
    public <T> Collection<T> find(java.lang.Class<T> entityClass) {
        if (entityClass.equals(Class.class)) {
            return (Collection<T>) classesCollection;
        } else if (entityClass.equals(Student.class)) {
            return (Collection<T>) studentsCollection;
        }
        return null;
    }

    @Override
    public <T> T load(java.lang.Class<T> entityClass, Serializable id) {
        if (entityClass.equals(Class.class)) {
            Class classResponse = classesCollection.stream()
                                                   .filter(c -> c.getId().equals(id))
                                                   .findFirst().orElse(null);

            return entityClass.cast(classResponse);
        } else if (entityClass.equals(Student.class)) {
            Student studentResponse = studentsCollection.stream()
                                                        .filter(s -> s.getId().equals(id))
                                                        .findFirst().orElse(null);

            return entityClass.cast(studentResponse);
        }

        return null;
    }

    @Override
    public <T> Collection<T> find(String customQuery) {
        return null;
    }

    @Override
    public boolean delete(Object entity) {
        boolean isRemoved = false;

        if (entity.getClass().equals(Class.class)) {
            Class classToDelete = Class.class.cast(entity);
            isRemoved = classesCollection.remove(classToDelete);
        } else if (entity.getClass().equals(Student.class)) {
            Student studentToDelete = Student.class.cast(entity);
            isRemoved = studentsCollection.remove(studentToDelete);
        }
        return isRemoved;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
