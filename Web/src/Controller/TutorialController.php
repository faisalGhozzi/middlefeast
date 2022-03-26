<?php

namespace App\Controller;

use App\Entity\Tutorial;
use App\Form\TutorialFormType;
use App\Repository\CommandeRepository;
use App\Repository\TutorialRepository;
use Doctrine\ORM\EntityManagerInterface;
use Exception;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Filesystem\Filesystem;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Exception\ExceptionInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class TutorialController extends AbstractController
{
    /**
     * @Route("/tutorial/", name="tutorial_index", methods={"GET"})
     * @param TutorialRepository $tutorialRepository
     * @return Response
     */
    public function index(TutorialRepository $tutorialRepository): Response
    {
        return $this->render('tutorial/index.html.twig', [
            'tutorials' => $tutorialRepository->findAll(),
        ]);
    }

    // JSON RESPONSES

    /**
     * @Route("/tutorial/json/new", name="newTutoJson", methods={"POST"})
     * @throws Exception
     */
    public function newTutoJson(Request $request): JsonResponse
    {
        $tutorial = new Tutorial();

        $em = $this->getDoctrine()->getManager();

        $tutorial->setDescription($request->get('description'));
        $tutorial->setCategory($request->get('category'));
        $tutorial->setDateTuto(new \DateTime($request->get('dateTuto')));

        $tutorial->setImage($request->get('image'));
        $tutorial->setVideo($request->get('video'));
        /*$imageFile = file_get_contents($request->get('image'));



        $videoFile = file_get_contents($request->get('video'));*/

        /*if($imageFile && $videoFile){
            echo $imageFile;
            $filesystem = new Filesystem();

            $path_info_image = pathinfo($imageFile);
            $path_info_video = pathinfo($videoFile);


            $imageFileName = md5(uniqid()).'.'.$path_info_image['extension'];
            $videoFileName = md5(uniqid()).'.'.$path_info_video['extension'];


            $filesystem->copy($imageFile, $this->getParameter('uploads_tutos').$imageFileName);
            $filesystem->copy($videoFile, $this->getParameter('uploads_tutos').$videoFileName);

          //  $imageFile->move($this->getParameter('uploads_tutos'), $imageFileName);
          //  $videoFile->move($this->getParameter('uploads_tutos'), $videoFileName);

            $tutorial->setImage($imageFileName);
            $tutorial->setVideo($videoFileName);

        }*/
        $tutorial->setPrix($request->get('prix'));
        $tutorial->setTitre($request->get('titre'));

        $em->persist($tutorial);
        $em->flush();

        return new JsonResponse($tutorial);
    }

    /**
     * @Route("/tutorial/json", name="TutoJson")
     * @throws ExceptionInterface
     */
    public function tutoJson(): JsonResponse
    {
        $tutorial = $this->getDoctrine()->getManager()
            ->getRepository(Tutorial::class)->findAll();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($tutorial);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/tutorial/json/update/{id}", name="updateTutoJson", methods={"POST"})
     * @throws Exception
     */
    public function updateTutoJson(Request $request, $id): JsonResponse
    {
        $em = $this->getDoctrine()->getManager();

        $tutorial = $em->getRepository(Tutorial::class)->find($id);

        $tutorial->setDescription($request->get('description'));
        $tutorial->setCategory($request->get('category'));
        $tutorial->setDateTuto(new \DateTime($request->get('dateTuto')));
        $tutorial->setImage($request->get('image'));
        $tutorial->setVideo($request->get('video'));
        $tutorial->setPrix($request->get('prix'));
        $tutorial->setTitre($request->get('titre'));

        $em->flush();

        return new JsonResponse($tutorial);
    }

    /**
     * @Route("/tutorial/json/{id}", name="TutoIdJson")
     * @throws ExceptionInterface
     */
    public function tutoIdJson($id): JsonResponse
    {
        $tutorial = $this->getDoctrine()->getManager()
            ->getRepository(Tutorial::class)->find($id);

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($tutorial);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/tutorial/json/delete/{id}", name="deleteTutoJson")
     */
    public function deleteTutoJson($id): JsonResponse
    {
        $tutorial = $this->getDoctrine()
            ->getRepository(Tutorial::class)->find($id);
        $this->getDoctrine()->getManager()->remove($tutorial);
        $this->getDoctrine()->getManager()->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($tutorial);
        return new JsonResponse($formatted);
    }

    // JSON Response DONE !!

    /**
     * @Route("/tutorial/{id}", name="tutorial_show", methods={"GET"})
     * @param CommandeRepository $commandeRepository
     * @param Tutorial $tutorial
     * @return Response
     */
    public function show(CommandeRepository $commandeRepository, Tutorial $tutorial): Response
    {
        $commande = $commandeRepository->findOneBy(['user'=>$this->getUser()]);

        return $this->render('tutorial/show.html.twig', [
            'tutorial' => $tutorial,
            'commande'=>$commande
        ]);
    }

    /**
     * @Route("/tutorial/{id}/edit", name="tutorial_edit", methods={"GET", "POST"})
     * @param Request $request
     * @param Tutorial $tutorial
     * @param EntityManagerInterface $entityManager
     * @return Response
     */
    public function edit(Request $request, Tutorial $tutorial, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(TutorialFormType::class, $tutorial);
                $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            /** @var UploadedFile $videoFile */
            $videoFile = $form->get('video')->getData();
            if($videoFile)
            {
                $originalFilename = pathinfo($videoFile->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = $originalFilename.'-'.uniqid().'.'.$videoFile->guessExtension();
                try {
                    $videoFile->move(
                        $this->getParameter('uploads'),
                        $newFilename
                    );
                } catch (FileException $e) {
                }

                $tutorial->setVideo($newFilename);
                $entityManager->flush();
                return $this->redirectToRoute('tutorial_index', [], Response::HTTP_SEE_OTHER);
            }else{
                //$tutorial->setVideo(new File($this->getParameter('uploads').'/'.$tutorial->getVideo()));
                $entityManager->flush();

            }

        }

        return $this->render('tutorial/edit.html.twig', [
            'tutorial' => $tutorial,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/tutorial/{id}", name="tutorial_delete", methods={"POST"})
     * @param Request $request
     * @param Tutorial $tutorial
     * @param EntityManagerInterface $entityManager
     * @return Response
     */
    public function delete(Request $request, Tutorial $tutorial, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$tutorial->getId(), $request->request->get('_token'))) {
            $entityManager->remove($tutorial);
            $entityManager->flush();
        }

        return $this->redirectToRoute('tutorial_index', [], Response::HTTP_SEE_OTHER);
    }
}