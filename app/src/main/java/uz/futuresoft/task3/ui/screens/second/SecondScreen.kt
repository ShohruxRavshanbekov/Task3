package uz.futuresoft.task3.ui.screens.second

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.futuresoft.task3.R
import uz.futuresoft.task3.databinding.ScreenSecondBinding
import uz.futuresoft.task3.utils.loadImage
import uz.futuresoft.task3.utils.onAnimationEnd
import uz.futuresoft.task3.utils.onAnimationStart

class SecondScreen : Fragment() {

    private val binding by lazy { ScreenSecondBinding.inflate(layoutInflater) }

    private var itemId = -1
    private var isMainInfoCardExpanded = false
    private var isCadastreInfoCardExpanded = false
    private var isProhibitionsOnPropertyInfoCardExpanded = false
    private var isPhotosCardExpanded = false

    // Sample data for adapters
    private val totalNumberOfFloors = listOf("12")
    private val floors = listOf("4")
    private val numberOfRooms = listOf("2")
    private val constructionDates = listOf("2000-2010")
    private val constructionTypes = listOf("Penablok")
    private val levelsOfRepair = listOf("Ishchi xolatda")
    private val unitsOfComparison = listOf("Umumiy maydon")
    private val typesOfSearch = listOf("Kadastr nomer bo’yicha qidirish")

    // Variables for checking text field's data is null or not
    private var numberOfFloor = ""
    private var houseLocatedFloor = ""
    private var numberOfRoom = ""
    private var constructedDate = ""
    private var constructionType = ""
    private var leverOfRepair = ""
    private var totalArea = ""
    private var constructionArea = ""
    private var usefulArea = ""
    private var livingArea = ""
    private var unitOfComparison = ""
    private var cadastreNumber = ""
    private var owner = ""
    private var pinflOrInn = ""

    // Variables for images
    private var appearance1Clicked = false
    private var appearance2Clicked = false
    private var entranceClicked = false
    private var outerDoorClicked = false
    private var corridorClicked = false
    private var bathroomClicked = false
    private var toiletClicked = false
    private var kitchenClicked = false

    private var appearance1: Uri? = null
    private var appearance2: Uri? = null
    private var entrance: Uri? = null
    private var outerDoor: Uri? = null
    private var corridor: Uri? = null
    private var bathroom: Uri? = null
    private var toilet: Uri? = null
    private var kitchen: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemId = arguments?.getInt("itemId")!!

        // AutocompleteTextView adapters
        val totalNumberOfFloorsAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            totalNumberOfFloors
        )
        val floorsAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            floors
        )
        val numberOfRoomsAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            numberOfRooms
        )
        val constructionDatesAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            constructionDates
        )
        val constructionTypesAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            constructionTypes
        )
        val levelOfRepairAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            levelsOfRepair
        )
        val unitsOfComparisonAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            unitsOfComparison
        )
        val typesOfSearchAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            typesOfSearch
        )

        // Setting adapter
        binding.totalNumberOfFloors.setAdapter(totalNumberOfFloorsAdapter)
        binding.houseLocatedFloor.setAdapter(floorsAdapter)
        binding.numberOfRooms.setAdapter(numberOfRoomsAdapter)
        binding.constructionTime.setAdapter(constructionDatesAdapter)
        binding.constructionType.setAdapter(constructionTypesAdapter)
        binding.levelOfRepair.setAdapter(levelOfRepairAdapter)
        binding.unitOfComparison.setAdapter(unitsOfComparisonAdapter)
        binding.searchType.setAdapter(typesOfSearchAdapter)

        // Checking id for setting text to title
        if (itemId == 0)
            binding.title.text = getString(R.string.kop_qavatli_uylarni_baholash)
        else
            binding.title.text = getString(R.string.hovli_uylarni_baholash)

        binding.totalNumberOfFloors.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = totalNumberOfFloors[position]
            numberOfFloor = selectedItem
            checkMainInfosFields()
        }

        binding.houseLocatedFloor.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = floors[position]
            houseLocatedFloor = selectedItem
            checkMainInfosFields()
        }

        binding.numberOfRooms.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = numberOfRooms[position]
            numberOfRoom = selectedItem
            checkMainInfosFields()
        }

        binding.constructionTime.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = constructionDates[position]
            constructedDate = selectedItem
            checkMainInfosFields()
        }

        binding.constructionType.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = constructionTypes[position]
            constructionType = selectedItem
            checkMainInfosFields()
        }

        binding.levelOfRepair.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = levelsOfRepair[position]
            leverOfRepair = selectedItem
            checkMainInfosFields()
        }

        binding.totalArea.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            totalArea = text.toString()
            checkMainInfosFields()
        })

        binding.constructionArea.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            constructionArea = text.toString()
            checkMainInfosFields()
        })

        binding.usefulArea.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            usefulArea = text.toString()
            checkMainInfosFields()
        })

        binding.livingArea.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            livingArea = text.toString()
            checkMainInfosFields()
        })

        binding.unitOfComparison.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = unitsOfComparison[position]
            unitOfComparison = selectedItem
            checkMainInfosFields()
        }

        binding.searchType.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = typesOfSearch[position]
            when (selectedItem) {
                "Kadastr nomer bo’yicha qidirish" -> {
                    binding.cadastreNumberContainer.isVisible = true
                }
            }
        }

        binding.cadastreNumber.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            cadastreNumber = text.toString()
            checkCadastreInfosFields()
        })

        binding.owner.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            owner = text.toString()
            checkCadastreInfosFields()
        })

        binding.pinflInn.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            pinflOrInn = text.toString()
            checkCadastreInfosFields()
        })

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.mainInfoCard.setOnClickListener {
            isMainInfoCardExpanded = !isMainInfoCardExpanded
            if (isMainInfoCardExpanded) expand(view = it) else hide(view = it)
            checkExpandability()
        }

        binding.cadastreInfoCard.setOnClickListener {
            isCadastreInfoCardExpanded = !isCadastreInfoCardExpanded
            if (isCadastreInfoCardExpanded) expand(view = it) else hide(view = it)
            checkExpandability()
        }

        binding.prohibitionsOnPropertyCard.setOnClickListener {
            isProhibitionsOnPropertyInfoCardExpanded = !isProhibitionsOnPropertyInfoCardExpanded
            if (isProhibitionsOnPropertyInfoCardExpanded) expand(view = it) else hide(view = it)
            checkExpandability()
        }

        binding.photosCard.setOnClickListener {
            isPhotosCardExpanded = !isPhotosCardExpanded
            if (isPhotosCardExpanded) expand(view = it) else hide(view = it)
            checkExpandability()
        }

        binding.imageAppearance.setOnClickListener {
            chooseImage(view = it)
        }

        binding.imageAppearance2.setOnClickListener {
            chooseImage(view = it)
        }

        binding.imageEntrance.setOnClickListener {
            chooseImage(view = it)
        }

        binding.imageOuterDoor.setOnClickListener {
            chooseImage(view = it)
        }

        binding.imageCorridor.setOnClickListener {
            chooseImage(view = it)
        }

        binding.imageBathroom.setOnClickListener {
            chooseImage(view = it)
        }

        binding.imageToilet.setOnClickListener {
            chooseImage(view = it)
        }

        binding.imageKitchen.setOnClickListener {
            chooseImage(view = it)
        }
    }

    private fun expand(view: View) {
        val animSlideDown = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_down)

        when (view) {
            binding.mainInfoCard -> {
                binding.mainInfoCard.setCardBackgroundColor(
                    ContextCompat.getColor(requireContext(), R.color.green1)
                )
                binding.mainInfoText.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.white)
                )
                binding.mainInfoExpandIcon.setImageResource(R.drawable.ic_arrow_top)
                binding.mainInfoExpandIcon.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                binding.mainInfoContainer.startAnimation(animSlideDown)
                animSlideDown.onAnimationStart { binding.mainInfoContainer.isVisible = true }
            }

            binding.cadastreInfoCard -> {
                binding.cadastreInfoCard.setCardBackgroundColor(
                    ContextCompat.getColor(requireContext(), R.color.green1)
                )
                binding.cadastreInfoText.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.white)
                )
                binding.cadastreInfoExpandIcon.setImageResource(R.drawable.ic_arrow_top)
                binding.cadastreInfoExpandIcon.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                binding.cadastreInfoContainer.startAnimation(animSlideDown)
                animSlideDown.onAnimationStart { binding.cadastreInfoContainer.isVisible = true }
            }

            binding.prohibitionsOnPropertyCard -> {
                binding.prohibitionsOnPropertyCard.setCardBackgroundColor(
                    ContextCompat.getColor(requireContext(), R.color.green1)
                )
                binding.prohibitionsOnPropertyText.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.white)
                )
                binding.prohibitionsOnPropertyExpandIcon.setImageResource(R.drawable.ic_arrow_top)
                binding.prohibitionsOnPropertyExpandIcon.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                binding.prohibitionsOnPropertyContainer.startAnimation(animSlideDown)
                animSlideDown.onAnimationStart {
                    binding.prohibitionsOnPropertyContainer.isVisible = true
                }
            }

            binding.photosCard -> {
                binding.photosCard.setCardBackgroundColor(
                    ContextCompat.getColor(requireContext(), R.color.green1)
                )
                binding.photosText.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.white)
                )
                binding.photosExpandIcon.setImageResource(R.drawable.ic_arrow_top)
                binding.photosExpandIcon.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                binding.photosContainer.startAnimation(animSlideDown)
                animSlideDown.onAnimationStart {
                    binding.photosContainer.isVisible = true
                }
            }
        }
    }

    private fun hide(view: View) {
        val animSlideUp = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        when (view) {
            binding.mainInfoCard -> {
                binding.mainInfoCard.setCardBackgroundColor(
                    ContextCompat.getColor(requireContext(), R.color.white)
                )
                binding.mainInfoText.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.text_color)
                )
                binding.mainInfoExpandIcon.setImageResource(R.drawable.ic_arrow_bottom)
                binding.mainInfoExpandIcon.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.icon_tint),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                binding.mainInfoContainer.startAnimation(animSlideUp)
                animSlideUp.onAnimationEnd { binding.mainInfoContainer.isVisible = false }
            }

            binding.cadastreInfoCard -> {
                binding.cadastreInfoCard.setCardBackgroundColor(
                    ContextCompat.getColor(requireContext(), R.color.white)
                )
                binding.cadastreInfoText.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.text_color)
                )
                binding.cadastreInfoExpandIcon.setImageResource(R.drawable.ic_arrow_bottom)
                binding.cadastreInfoExpandIcon.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.icon_tint),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                binding.cadastreInfoContainer.startAnimation(animSlideUp)
                animSlideUp.onAnimationEnd { binding.cadastreInfoContainer.isVisible = false }
            }

            binding.prohibitionsOnPropertyCard -> {
                binding.prohibitionsOnPropertyCard.setCardBackgroundColor(
                    ContextCompat.getColor(requireContext(), R.color.white)
                )
                binding.prohibitionsOnPropertyText.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.text_color)
                )
                binding.prohibitionsOnPropertyExpandIcon.setImageResource(R.drawable.ic_arrow_bottom)
                binding.prohibitionsOnPropertyExpandIcon.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.icon_tint),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                binding.prohibitionsOnPropertyContainer.startAnimation(animSlideUp)
                animSlideUp.onAnimationEnd {
                    binding.prohibitionsOnPropertyContainer.isVisible = false
                }
            }

            binding.photosCard -> {
                binding.photosCard.setCardBackgroundColor(
                    ContextCompat.getColor(requireContext(), R.color.white)
                )
                binding.photosText.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.text_color)
                )
                binding.photosExpandIcon.setImageResource(R.drawable.ic_arrow_bottom)
                binding.photosExpandIcon.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.icon_tint),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                binding.photosContainer.startAnimation(animSlideUp)
                animSlideUp.onAnimationEnd {
                    binding.photosContainer.isVisible = false
                }
            }
        }
    }

    private fun checkExpandability() {
        binding.rating.isVisible = !isMainInfoCardExpanded &&
                !isCadastreInfoCardExpanded &&
                !isProhibitionsOnPropertyInfoCardExpanded &&
                !isPhotosCardExpanded
    }

    private fun checkMainInfosFields() {
        binding.saveMainInfo.isEnabled = numberOfFloor.isNotEmpty() &&
                houseLocatedFloor.isNotEmpty() &&
                numberOfRoom.isNotEmpty() &&
                constructedDate.isNotEmpty() &&
                constructionType.isNotEmpty() &&
                leverOfRepair.isNotEmpty() &&
                totalArea.isNotEmpty() &&
                constructionArea.isNotEmpty() &&
                usefulArea.isNotEmpty() &&
                livingArea.isNotEmpty() &&
                unitOfComparison.isNotEmpty()
    }

    private fun checkCadastreInfosFields() {
        binding.saveCadastreInfo.isEnabled = cadastreNumber.isNotEmpty() &&
                owner.isNotEmpty() &&
                pinflOrInn.isNotEmpty()
    }

    private fun chooseImage(view: View) {
        when (view) {
            binding.imageAppearance -> appearance1Clicked = true
            binding.imageAppearance2 -> appearance2Clicked = true
            binding.imageEntrance -> entranceClicked = true
            binding.imageOuterDoor -> outerDoorClicked = true
            binding.imageCorridor -> corridorClicked = true
            binding.imageBathroom -> bathroomClicked = true
            binding.imageToilet -> toiletClicked = true
            binding.imageKitchen -> kitchenClicked = true
        }
        photosFromGallery.launch("image/*")
    }

    private val photosFromGallery =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                if (appearance1Clicked) {
                    appearance1 = uri
                    binding.imageAppearance.loadImage(
                        context = requireContext(),
                        image = appearance1!!
                    )
                    appearance1Clicked = false
                } else if (appearance2Clicked) {
                    appearance2 = uri
                    binding.imageAppearance2.loadImage(
                        context = requireContext(),
                        image = appearance2!!
                    )
                    appearance2Clicked = false
                } else if (entranceClicked) {
                    entrance = uri
                    binding.imageEntrance.loadImage(
                        context = requireContext(),
                        image = entrance!!
                    )
                    entranceClicked = false
                } else if (outerDoorClicked) {
                    outerDoor = uri
                    binding.imageOuterDoor.loadImage(
                        context = requireContext(),
                        image = outerDoor!!
                    )
                    outerDoorClicked = false
                } else if (corridorClicked) {
                    corridor = uri
                    binding.imageCorridor.loadImage(
                        context = requireContext(),
                        image = corridor!!
                    )
                    corridorClicked = false
                } else if (bathroomClicked) {
                    bathroom = uri
                    binding.imageBathroom.loadImage(
                        context = requireContext(),
                        image = bathroom!!
                    )
                    bathroomClicked = false
                } else if (toiletClicked) {
                    toilet = uri
                    binding.imageToilet.loadImage(
                        context = requireContext(),
                        image = toilet!!
                    )
                    toiletClicked = false
                } else {
                    kitchen = uri
                    binding.imageKitchen.loadImage(
                        context = requireContext(),
                        image = kitchen!!
                    )
                    kitchenClicked = false
                }
            }
        }
}