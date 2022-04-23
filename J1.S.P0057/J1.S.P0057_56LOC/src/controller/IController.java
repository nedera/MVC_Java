/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;

/**
 *
 * @author nghie
 */
public interface IController {
    <T> void writeToFile(List<T> list, String fileName);
    <T> List<T> readFromFile(String fileName);
//    <T> List<T> readFromFile(String fileName);
    
}
