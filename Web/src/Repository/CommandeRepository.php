<?php

namespace App\Repository;

use App\Entity\Commande;
use App\Entity\User;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\NonUniqueResultException;
use Doctrine\ORM\Query\Expr\Join;
use Doctrine\ORM\Query\ResultSetMappingBuilder;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Commande|null find($id, $lockMode = null, $lockVersion = null)
 * @method Commande|null findOneBy(array $criteria, array $orderBy = null)
 * @method Commande[]    findAll()
 * @method Commande[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class CommandeRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Commande::class);
    }

    /**
     * @return void
     * @throws NonUniqueResultException
     */
    public function search($words){
//        $entityManager = $this->getEntityManager();

//        $rsm = new ResultSetMappingBuilder($entityManager);
//        $rsm->addRootEntityFromClassMetadata('App\Entity\Commande', 'c');
//        $rsm->addJoinedEntityFromClassMetadata('App\Entity\User', 'u', 'c' , 'user', array('id' => 'user'));
//
//        $query = $this->$entityManager->createNativeQuery(
//            'SELECT * FROM c INNER JOIN u ON c.user = u.id WHERE MATCH_AGAINST(u.firstname, u.lastname) AGAINST(?)', $rsm
//        );
//
//
//        return $query->getOneOrNullResult();

        $query = $this->createQueryBuilder('c');
        if($words != null){
            $query->where('MATCH_AGAINST(c.user.firstname, c.user.lastname) AGAINST (:words boolean)>0')
                ->setParameter('words', $words);
        }
        return $query->getQuery()->getResult();
    }

    // /**
    //  * @return Commande[] Returns an array of Commande objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('c.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Commande
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
