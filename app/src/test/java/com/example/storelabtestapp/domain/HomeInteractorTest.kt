package com.example.storelabtestapp.domain

import com.example.freshegokidproject.rules.RxSchedulerRule
import com.example.storelabtestapp.data.model.home.GalleryImage
import com.example.storelabtestapp.data.repository.home.HomeRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test

class HomeInteractorTest {
    @get:Rule
    val schedulerRule = RxSchedulerRule()

    // Mock the HomeRepository
    private val mockRepository: HomeRepository = mockk()

    // Create the HomeInteractor with the mock HomeRepository
    private val homeInteractor = HomeInteractor(mockRepository)

    @Test
    fun `getGalleryImagesWithPageNoAndLimit should return gallery images`() {
        // Arrange
        val pageNo = 1
        val limit = 10
        val testGalleryImages = listOf(
            GalleryImage(id = "1", url = "https://picsum.photos/id/29/200"),
            GalleryImage(id = "2", url = "https://picsum.photos/id/30/200"),
            GalleryImage(id = "3", url = "https://picsum.photos/id/31/200")
        )
        every { mockRepository.fetchGalleryImagesList(any(), any()) } returns Single.just(testGalleryImages)

        // Act
        val testObserver = homeInteractor.getGalleryImagesByPageNoAndLimit(pageNo, limit).test()

        // Assert
        testObserver.assertNoErrors()
        testObserver.assertSubscribed()
    }

}