/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author bouss
 */
public interface IProduit <T> {
    public void Add(T t) ;
    public void Update(T t);
    public void Delete(int id);
    public T GetById(int id);
    public List GetAll();
}
