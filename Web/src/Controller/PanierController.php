<?php

namespace App\Controller;

use App\Entity\Formation;
use App\Entity\Panier;
use App\Entity\Tutorial;
use App\Entity\User;
use App\Form\PanierType;
use App\Repository\PanierRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Exception\ExceptionInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

/**
 * @Route("/panier")
 */
class PanierController extends AbstractController
{
    /**
     * @Route("/", name="panier_index", methods={"GET"})
     * @param PanierRepository $panierRepository
     * @return Response
     */
    public function index(PanierRepository $panierRepository): Response
    {
        if($this->getUser()!=null)
        {
            return $this->render('panier/index.html.twig', [
                'paniers' => $panierRepository->findBy(['user'=>$this->getUser()]),
            ]);
        }else
        {
            return $this->redirectToRoute('app_login', [], Response::HTTP_SEE_OTHER);

        }

    }

    // JSON RESPONSES

    /**
     * @Route("/json", name="PanierJson")
     * @throws ExceptionInterface
     */
    public function formationJson(): JsonResponse
    {
        $formation = $this->getDoctrine()->getManager()
            ->getRepository(Formation::class)->findAll();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($formation);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/json/addTuto", name="addTutoPanierJson")
     */
    public function addTutoPanierJson(Request $request): JsonResponse
    {
        $formation = new Formation();

        $em = $this->getDoctrine()->getManager();

        $formation->setPrice($request->get('price'));
        $formation->setDateDebut($request->get('date_debut'));
        $formation->setDateFin($request->get('date_fin'));
        $formation->setDescription($request->get('description'));
        $formation->setDuree($request->get('duree'));
        $formation->setMode($request->get('mode'));

        $em->persist($formation);
        $em->flush();

        return new JsonResponse($formation);
    }

    /**
     * @Route("/json/addFormation", name="addFormationPanierJson")
     */
    public function addFormationPanierJson(Request $request): JsonResponse
    {
        $formation = new Formation();

        $em = $this->getDoctrine()->getManager();

        $formation->setPrice($request->get('price'));
        $formation->setDateDebut($request->get('date_debut'));
        $formation->setDateFin($request->get('date_fin'));
        $formation->setDescription($request->get('description'));
        $formation->setDuree($request->get('duree'));
        $formation->setMode($request->get('mode'));

        $em->persist($formation);
        $em->flush();

        return new JsonResponse($formation);
    }

    /**
     * @Route("/json/update/{id}", name="updatePanierJson")
     */
    public function updateFormationJson(Request $request, $id): JsonResponse
    {
        $em = $this->getDoctrine()->getManager();

        $formation = $em->getRepository(Formation::class)->find($id);

        $formation->setDateDebut($request->get("date_debut"));
        $formation->setDateFin($request->get("date_fin"));
        $formation->setDescription($request->get("description"));
        $formation->setDuree($request->get("duree"));
        $formation->setMode($request->get("mode"));
        $formation->setPrice($request->get("price"));

        $em->flush();

        return new JsonResponse($formation);
    }

    /**
     * @Route("/json/{id}", name="PanierOneJson")
     * @throws ExceptionInterface
     */
    public function formationIdJson($id): JsonResponse
    {
        $formation = $this->getDoctrine()->getManager()
            ->getRepository(Formation::class)->find($id);

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($formation);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/json/delete/{id}", name="deletePanierJson")
     */
    public function deleteFormationJson($id)
    {
        $formation = $this->getDoctrine()
            ->getRepository(Formation::class)->find($id);
        $this->getDoctrine()->getManager()->remove($formation);
        $this->getDoctrine()->getManager()->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($formation);
        return new JsonResponse($formatted);
    }


    /**
     * @param Request $request
     * @param EntityManagerInterface $entityManager
     * @param $id
     * @return Response
     * @Route("/addTuto/{id}",name="add_tuto_cart")
     */
    public function createCartItemTutorial(Request $request,EntityManagerInterface $entityManager,$id):Response
    {
        if($this->getUser()!=null)
        {
            $user= $this->getUser();
            $tutorial = $entityManager->getRepository(Tutorial::class)->find($id);

                 $tutorialexist = $entityManager->getRepository('App:Panier')->findOneBy(['tutorial'=>$id]);
                 $formationexist = $entityManager->getRepository('App:Panier')->findOneBy(['formation'=>$id]);

                 if($tutorialexist && !$formationexist)
                 {
                     $tutorialexist->setQte($tutorialexist->getQte()+1);
                     $tutorialexist->setTotal($tutorialexist->getQte()*$tutorial->getPrix());
                     $entityManager->flush();
                 }else{
                    $panier = new Panier();
                    $panier->setTutorial($tutorial);
                    $panier->setFormation(null);
                    $panier->setUser($user);
                    $panier->setQte(1);
                    $panier->setTotal($panier->getQte()*$tutorial->getPrix());
                    $entityManager->persist($panier);
                    $entityManager->flush();
                 }

            return $this->redirectToRoute('tutorial_index', [], Response::HTTP_SEE_OTHER);

        }
        return $this->redirectToRoute('app_login', [], Response::HTTP_SEE_OTHER);
    }

    /**
     * @param Request $request
     * @param EntityManagerInterface $entityManager
     * @param $id
     * @return Response
     * @Route("/addFormation/{id}",name="add_formation_cart")
     */
    public function createCartItemFormation(Request $request,EntityManagerInterface $entityManager,$id):Response
    {
        if($this->getUser()!=null)
        {
            $user=$this->getUser();
            $formation = $entityManager->getRepository(Formation::class)->find($id);

            $formationexist = $entityManager->getRepository('App:Panier')->findOneBy(['formation'=>$id]);



            if($formationexist)
            {
                $formationexist->setQte($formationexist->getQte()+1);
                $formationexist->setTotal($formationexist->getQte()*$formation->getPrice());
                $entityManager->flush();
            }else{
                $panier = new Panier();
                $panier->setFormation($formation);
                $panier->setTutorial(null);
                $panier->setUser($user);
                $panier->setQte(1);
                $panier->setTotal($panier->getQte()*$formation->getPrice());
                $entityManager->persist($panier);
                $entityManager->flush();
            }

            return $this->redirectToRoute('formation_index', [], Response::HTTP_SEE_OTHER);
        }
        return $this->redirectToRoute('app_login', [], Response::HTTP_SEE_OTHER);
    }


    /**
     * @Route("/delete/{id}", name="panier_delete")
     * @param EntityManagerInterface $entityManager
     * @return Response
     */
    public function delete(EntityManagerInterface $entityManager,$id): Response
    {
            $panier = $entityManager->getRepository(Panier::class)->find($id);
            if($panier)
            {
                $entityManager->remove($panier);
                $entityManager->flush();
            }
        return $this->redirectToRoute('panier_index', [], Response::HTTP_SEE_OTHER);
    }


    /**
     * @param EntityManagerInterface $entityManager
     * @param $id
     * @param $quantity
     * @param $idp
     * @Route("/updateFormationCart/{id}/{quantity}/{idp}",name="update_formation_cart",methods={"GET", "POST"})
     * @return RedirectResponse
     */
    public function updateQuantityFormation(EntityManagerInterface $entityManager, $id, $quantity, $idp)
    {
        $panier = $entityManager->getRepository(Panier::class)->find($id);

        $formation = $entityManager->getRepository(Formation::class)->find($idp);


        if($panier && $formation)
        {
            $panier->setQte( (int) $quantity);
            $panier->setTotal($formation->getPrice()*(int)$quantity);
            $entityManager->flush();
        }
        return $this->redirectToRoute('panier_index', [], Response::HTTP_SEE_OTHER);

    }

    /**
     * @param EntityManagerInterface $entityManager
     * @param $id
     * @param $quantity
     * @param $idp
     * @Route("/updateTutorialCart/{id}/{quantity}/{idp}",name="update_tutorial_cart",methods={"GET", "POST"})
     * @return RedirectResponse
     */
    public function updateQuantityTutorial(EntityManagerInterface $entityManager, $id, $quantity, $idp)
    {
        $panier = $entityManager->getRepository(Panier::class)->find($id);
        $tutorial = $entityManager->getRepository(Tutorial::class)->find($idp);

        if($panier && $tutorial)
        {
            $panier->setQte((int)$quantity);
            $panier->setTotal($tutorial->getPrix()*(int)$quantity);
            $entityManager->flush();
        }
        return $this->redirectToRoute('panier_index', [], Response::HTTP_SEE_OTHER);

    }
}
