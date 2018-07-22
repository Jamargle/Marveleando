package jmlb0003.com.marveleando.domain.interactor;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.domain.repository.CharacterLocalRepository;

public final class UpdateCharacter extends UseCase<Character, Void> {

    public static final String NAME = "InjectionKey:UpdateCharactersUseCase";

    private final CharacterLocalRepository characterLocalRepository;

    @Inject
    public UpdateCharacter(
            final CharacterLocalRepository characterLocalRepository,
            final ThreadExecutor threadExecutor,
            final PostExecutionThread postExecutionThread) {

        super(threadExecutor, postExecutionThread);
        this.characterLocalRepository = characterLocalRepository;
    }

    @Override
    protected Observable<Void> buildUseCaseObservable(@Nullable final Character params) {
        if (params != null) {
            return Observable.create(new ObservableOnSubscribe<Void>() {
                @Override
                public void subscribe(final ObservableEmitter<Void> emitter) {
                    characterLocalRepository.update(params);
                    emitter.onComplete();
                }
            });
        } else {
            return Observable.error(new RuntimeException());
        }
    }

}
