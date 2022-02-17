<?php

namespace App\Form;

use App\Entity\Tutorial;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class TutorialFormType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder

            ->add('dateTuto',DateTimeType::class,[
                'label'=>'Tutorial Date'
            ])
            ->add('category',ChoiceType::class,[
                'choices' => [
                    'Pick Category' => 'Pick Category',
                    'Sweet ' => 'Sweet',
                    'Salted' => 'Salted'
                ],
                'expanded'=>false,
                'multiple'=>false])

            ->add('titre',TextType::class,[
                'label'=>'Title',
                'attr' => [
                    'placeholder' => 'Title',
                ]])
            ->add('description',TextType::class,[
                'label'=>'Description',
                'attr' => [
                    'placeholder' => 'Description',
                ]])
            ->add('prix',NumberType::class,[
                'label'=>'Price',
                'attr' => [
                    'placeholder' => 'Price',
                ]])


        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Tutorial::class,
        ]);
    }
}
