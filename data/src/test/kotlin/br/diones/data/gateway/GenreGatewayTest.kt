package br.diones.data.gateway

import br.diones.data.local.model.GenreLocalModel
import br.diones.data.repository.GenreRepository
import br.diones.domain.entity.Genre
import br.diones.domain.gateway.GenreGateway
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class GenreGatewayTest {

    @Mock
    private lateinit var genreRepository: GenreRepository

    private lateinit var genreGateway: GenreGateway


    @Before
    fun setUp() {
        genreGateway = GenreGatewayImpl(genreRepository)
    }

    @Test
    @Throws(Exception::class)
    fun `Given genre items data, When get genres, Should fetch data from repository then parse to domain data`() {

        // Given
        val domainItems = listOf(Genre(1, "name"))
        val repositoryItems = listOf(GenreLocalModel(1, "name"))
        `when`(genreRepository.getAll()).thenReturn(Observable.just(repositoryItems))

        // When
        val testObserver = genreGateway.getGenres()
                .test()

        // Should
        testObserver
                .assertComplete()
                .assertNoErrors()
                .assertValue(domainItems)
    }
}