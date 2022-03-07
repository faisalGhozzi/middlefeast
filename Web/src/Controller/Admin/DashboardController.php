<?php

namespace App\Controller\Admin;

use App\Repository\CommandeRepository;
use App\Repository\FormationRepository;
use App\Repository\TutorialRepository;
use Doctrine\ORM\NonUniqueResultException;
use Doctrine\ORM\NoResultException;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


/**
 * Class DashboardController
 * @package App\Controller\Admin
 * @Route("/admin")
 */

class DashboardController extends AbstractController{
    /**
     * @Route("/",name="admin")
     */
    public function index(CommandeRepository $commandeRepository, FormationRepository $formationRepository, TutorialRepository $tutorialRepository):Response
    {
            return $this->render('admin/home/home.html.twig', [
                'count_orders' => $commandeRepository->countCommand() > 0 ? $commandeRepository->countCommand() : 0,
                'sum_orders' => $commandeRepository->sumCommand() > 0 ? $commandeRepository->sumCommand() : 0,
                'count_formation' => $formationRepository->countFormation(),
                'count_tutorial' => $tutorialRepository->countTutorial(),
            ]);
    }
}