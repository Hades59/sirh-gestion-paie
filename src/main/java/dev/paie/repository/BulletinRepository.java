/**
 * 
 */
package dev.paie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.BulletinSalaire;

/**
 * @author ETY3
 *
 */
public interface BulletinRepository extends JpaRepository<BulletinSalaire, Integer>{

}
