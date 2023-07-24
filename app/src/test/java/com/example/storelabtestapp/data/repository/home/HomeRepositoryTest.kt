package com.example.storelabtestapp.data.repository.home

import com.example.storelabtestapp.data.model.home.GalleryImage
import com.example.storelabtestapp.data.remote.home.HomeService
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class HomeRepositoryTest {

    // Mock the HomeService
    private val mockHomeService: HomeService = mockk()

    // Create the HomeRepository with the mock HomeService
    private val homeRepository = HomeRepository(mockHomeService)

    // Create some test data
    private val testGalleryImages = listOf(
        GalleryImage(id = "1", url = "https://picsum.photos/id/29/200"),
        GalleryImage(id = "2", url = "https://picsum.photos/id/30/200"),
        GalleryImage(id = "3", url = "https://picsum.photos/id/31/200")
    )

    @Before
    fun setUp() {
        // Reset the mock before each test
        every { mockHomeService.getGalleryImageList(any(), any()) } returns Single.just(testGalleryImages)
    }

    @Test
    fun `fetchGalleryImagesList should return the list of gallery images`() {
        // Arrange
        val pageNumber = 1
        val listLimit = 10

        // Act
        val testObserver = homeRepository.fetchGalleryImagesList(pageNumber, listLimit).test()

        // Assert
        testObserver.assertValue(testGalleryImages)
        testObserver.assertComplete()
    }

    @Test
    fun `fetchGalleryImagesList should return an error if the network request fails`() {
        // Arrange
        val pageNumber = 1
        val listLimit = 10
        val error = Throwable("Network Error")

        // Set up the mock to return an error
        every { mockHomeService.getGalleryImageList(any(), any()) } returns Single.error(error)

        // Act
        val testObserver = homeRepository.fetchGalleryImagesList(pageNumber, listLimit).test()

        // Assert
        testObserver.assertError(error)
    }
}