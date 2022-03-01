<?php

namespace App\Controller;

use App\Form\EditProfileType;
use App\Entity\User;
use phpDocumentor\Reflection\Types\This;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Serializer\Exception\ExceptionInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

/**
 * Class UserController
 * @package App\Controller
 * @Route("/users")
 */

class UserController extends AbstractController
{
    /**
     * @Route("/", name="users")
     */
    public function users(){
        return $this->render('users/index.html.twig');
    }

    // JSON RESPONSES

    /**
     * @Route("/json/new", name="newUserJsonAction")
     */
    public function newUserJsonAction(Request $request, UserPasswordEncoderInterface $passwordEncoder): JsonResponse
    {
        $user = new User();

        $em = $this->getDoctrine()->getManager();

        if($request->get('pass') == $request->get('pass2')){
            $user->setEmail($request->get('email'));
            $user->setFirstname($request->get('firstname'));
            $user->setLastname($request->get('lastname'));
            $user->setIsVerified(1);
            $user->setPassword($passwordEncoder->encodePassword($user, $request->get('pass')));
            $em->flush();
            $em->persist($formation);
            $em->flush();
            return new JsonResponse($user);
            }
        return new JsonResponse();
    }

    /**
     * @Route("/json", name="UserJsonAction")
     * @throws ExceptionInterface
     */
    public function userJsonAction(): JsonResponse
    {
        $user = $this->getDoctrine()->getManager()
            ->getRepository(User::class)->findAll();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($user);
        return new JsonResponse($formatted);
    }

    // public function updateFormationJson(Request $request, $id): JsonResponse
    // {
    //     $em = $this->getDoctrine()->getManager();

    //     $formation = $em->getRepository(Formation::class)->find($id);

    //     $formation->setDateDebut($request->get("date_debut"));
    //     $formation->setDateFin($request->get("date_fin"));
    //     $formation->setDescription($request->get("description"));
    //     $formation->setDuree($request->get("duree"));
    //     $formation->setMode($request->get("mode"));
    //     $formation->setPrice($request->get("price"));

    //     $em->flush();

    //     return new JsonResponse($formation);
    // }

    // public function formationIdJson($id): JsonResponse
    // {
    //     $formation = $this->getDoctrine()->getManager()
    //         ->getRepository(Formation::class)->find($id);

    //     $serializer = new Serializer([new ObjectNormalizer()]);
    //     $formatted = $serializer->normalize($formation);
    //     return new JsonResponse($formatted);
    // }

    /**
     * @Route("/json/delete/{id}", name="deleteUserJsonAction")
     */
    public function deleteUserJsonAction($id)
    {
        $user = $this->getDoctrine()
            ->getRepository(User::class)->find($id);
        $this->getDoctrine()->getManager()->remove($user);
        $this->getDoctrine()->getManager()->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($user);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/updateprofile", name="update_user_profile")
     */
    public function updateUserProfile(Request $request)
    {
        $user = $this->getUser();
        $form = $this->createForm(EditProfileType::class, $user);

        $form->handleRequest($request);

            if($form->isSubmitted() && $form->isValid()){
                $em = $this->getDoctrine()->getManager();
                $em->persist($user);
                $em->flush();

                $this->addFlash('message', 'Profile is updated');
                return $this->redirectToRoute('users');
            }

        return $this->render('users/edit_profile.html.twig',[
            'editProfileForm' => $form->createView(),
        ]);
    }

    /**
     * @Route("/changepassword", name="change_user_password")
     */
    public function changeUserPassword(Request $request, UserPasswordEncoderInterface $passwordEncoder)
    {
        if($request->isMethod('POST')){

            $em = $this->getDoctrine()->getManager();

            $user = $this->getUser();
            if($passwordEncoder->isPasswordValid($user, $request->request->get('old-pass'))){
                if($request->request->get('pass') == $request->request->get('pass2')){
                    $user->setPassword($passwordEncoder->encodePassword($user, $request->request->get('pass')));
                    $em->flush();

                    return $this->redirectToRoute('users');
                }else{
                    $this->addFlash('error', 'The passwords are not identical');
                }
            }else{
                $this->addFlash('error', 'Old Password is not valid');
            }

        }

        return $this->render('users/edit_password.html.twig');
    }
}