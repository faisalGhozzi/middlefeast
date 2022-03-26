<?php

namespace App\Controller;

use App\Entity\Formation;
use App\Form\FormationFormType;
use App\Repository\FormationRepository;
use Doctrine\ORM\EntityManagerInterface;
use Exception;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Exception\ExceptionInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class FormationController extends AbstractController
{
    /**
     * @Route("/formation/", name="formation_index")
     * @param FormationRepository $formationRepository
     * @return Response
     */
    public function index(FormationRepository $formationRepository): Response
    {
        return $this->render('formation/index.html.twig', [
            'formations' => $formationRepository->findAll(),
        ]);
        
    }

    /**
     * @Route("/formation/new", name="formation_new")
     * @param Request $request
     * @param EntityManagerInterface $entityManager
     * @return Response
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $formation = new Formation();
        $form = $this->createForm(FormationFormType::class, $formation);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()){
            $entityManager->persist($formation);
            $entityManager->flush();

            return $this->redirectToRoute('formation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('formation/add.html.twig', [
            'formation' => $formation,
            'form' => $form->createView(),
        ]);
    }

    // JSON RESPONSES

    /**
     * @Route("/formation/json", name="FormationJsonAction")
     * @throws ExceptionInterface
     */
    public function formationJsonAction(): JsonResponse
    {
        $formation = $this->getDoctrine()->getManager()
            ->getRepository(Formation::class)->findAll();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($formation);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/formation/json/new", name="newFormationJson", methods={"POST"})
     * @throws Exception
     */
    public function newFormationJson(Request $request): JsonResponse
    {
        $formation = new Formation();

        $em = $this->getDoctrine()->getManager();

        $formation->setPrice($request->get('price'));
        $formation->setDateDebut(new \DateTime($request->get('dateDebut')));
        $formation->setDateFin(new \DateTime($request->get('dateFin')));
        $formation->setDescription($request->get('description'));
        $formation->setDuree($request->get('duree'));
        $formation->setMode($request->get('mode'));

        $em->persist($formation);
        $em->flush();

        return new JsonResponse($formation);
    }

    /**
     * @Route("/formation/json/update/{id}", name="updateFormationJson")
     * @throws Exception
     */
    public function updateFormationJson(Request $request, $id): JsonResponse
    {
        $em = $this->getDoctrine()->getManager();

        $formation = $em->getRepository(Formation::class)->find($id);

        $formation->setDateDebut(new \DateTime($request->get("dateDebut")));
        $formation->setDateFin(new \DateTime($request->get("dateFin")));
        $formation->setDescription($request->get("description"));
        $formation->setDuree($request->get("duree"));
        $formation->setMode($request->get("mode"));
        $formation->setPrice($request->get("price"));

        $em->flush();

        return new JsonResponse($formation);
    }

    /**
     * @Route("/formation/json/{id}", name="FormationIdJson")
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
     * @Route("/formation/json/delete/{id}", name="deleteFormationJsonAction")
     * @throws ExceptionInterface
     */
    public function deleteFormationJsonAction($id): JsonResponse
    {
        $formation = $this->getDoctrine()
            ->getRepository(Formation::class)->find($id);
        $this->getDoctrine()->getManager()->remove($formation);
        $this->getDoctrine()->getManager()->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($formation);
        return new JsonResponse($formatted);
    }

    // JSON RESPONSES DONE !!!

    /**
     * @Route("/formation/{id}", name="formation_show")
     * @param Formation $formation
     * @return Response
     */
    public function show(Formation $formation): Response
    {
        return $this->render('formation/show.html.twig',[
            'formation' => $formation,
        ]);
    }

    /**
     * @Route("/formation/{id}/edit", name="formation_edit")
     * @param Request $request
     * @param Formation $formation
     * @param EntityManagerInterface $entityManager
     * @return Response
     */
    public function edit(Request $request, Formation $formation, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(FormationFormType::class, $formation);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()){
            $entityManager->flush();
            return $this->redirectToRoute('formation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('formation/edit.html.twig', [
            'formation' => $formation,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/formation/{id}", name="formation_delete")
     * @param Request $request
     * @param Formation $formation
     * @param EntityManagerInterface $entityManager
     * @return Response
     */
    public function delete(Request $request, Formation $formation, EntityManagerInterface $entityManager): Response
    {
        if($this->isCsrfTokenValid('delete'.$formation->getId(), $request->request->get('_token'))){
            $entityManager->remove($formation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('formation_index', [], Response::HTTP_SEE_OTHER);
    }
}
