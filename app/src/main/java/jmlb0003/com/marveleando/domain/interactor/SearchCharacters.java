package jmlb0003.com.marveleando.domain.interactor;

import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.domain.repository.CharacterLocalRepository;
import jmlb0003.com.marveleando.domain.repository.CharacterNetworkRepository;

public final class SearchCharacters extends UseCase<SearchCharacters.Input, List<Character>> {

    public static final String NAME = "InjectionKey:SearchCharactersUseCase";

    private final CharacterLocalRepository characterLocalRepository;
    private final CharacterNetworkRepository characterNetworkRepository;

    @Inject
    public SearchCharacters(
            final CharacterLocalRepository characterLocalRepository,
            final CharacterNetworkRepository characterNetworkRepository,
            final ThreadExecutor threadExecutor,
            final PostExecutionThread postExecutionThread) {

        super(threadExecutor, postExecutionThread);
        this.characterLocalRepository = characterLocalRepository;
        this.characterNetworkRepository = characterNetworkRepository;
    }

    @Override
    protected Observable<List<Character>> buildUseCaseObservable(@Nullable final Input params) {
        return Observable.create(new ObservableOnSubscribe<List<Character>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<Character>> emitter) {
                if (params == null || (params.currentPage <= 1 && params.query == null)) {
                    fetchBeginningCharacters(emitter);
                } else {
                    emitter.onNext(characterNetworkRepository.getCharactersByName(
                            params.getCurrentPage(),
                            params.getQuery()));
                }
            }
        });
    }

    private void fetchBeginningCharacters(final ObservableEmitter<List<Character>> emitter) {
        if (characterLocalRepository.beginningCharactersAreValid()) {
            emitter.onNext(characterLocalRepository.getBeginningCharacters());
        } else {
            final List<Character> charactersFromNetwork = characterNetworkRepository.getCharacters();
            characterLocalRepository.refreshBeginningCharactersIfNeeded(charactersFromNetwork);
            emitter.onNext(charactersFromNetwork);
        }
    }

    public static final class Input {

        private int currentPage;
        private String query;

        public Input(
                final int currentPage,
                @Nullable final String query) {

            this.currentPage = currentPage;
            this.query = query;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        @Nullable
        public String getQuery() {
            return query;
        }

    }

}
