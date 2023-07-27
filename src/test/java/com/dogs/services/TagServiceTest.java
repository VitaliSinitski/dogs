package com.dogs.services;

import com.dogs.dto.TagDto;
import com.dogs.entities.Tag;
import com.dogs.mappers.TagMapper;
import com.dogs.repositories.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    private TagRepository tagRepository;

    @Mock
    private TagMapper tagMapper;

    @InjectMocks
    private TagService tagService;

    @Test
    void testFindAll() {
        // Given
        List<Tag> tagList = List.of(new Tag(1L, "Tag1"), new Tag(2L, "Tag2"));
        when(tagRepository.findAll()).thenReturn(tagList);

        TagDto tagDto1 = new TagDto(1L, "Tag1");
        TagDto tagDto2 = new TagDto(2L, "Tag2");
        when(tagMapper.mapToDto(tagList.get(0))).thenReturn(tagDto1);
        when(tagMapper.mapToDto(tagList.get(1))).thenReturn(tagDto2);

        // When
        List<TagDto> result = tagService.findAll();

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyInAnyOrder(tagDto1, tagDto2);
    }

    @Test
    void testFindAllWhenNoTagsFound() {
        // Given
        when(tagRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<TagDto> result = tagService.findAll();

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void testFindById() {
        // Given
        Long tagId = 1L;
        Tag tag = new Tag(tagId, "Tag1");
        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));

        TagDto tagDto = new TagDto(tagId, "Tag1");
        when(tagMapper.mapToDto(tag)).thenReturn(tagDto);

        // When
        TagDto result = tagService.findById(tagId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(tagId);
        assertThat(result.getName()).isEqualTo("Tag1");
    }

    @Test
    void testFindByIdWhenTagNotFound() {
        // Given
        Long nonExistentTagId = 999L;
        when(tagRepository.findById(nonExistentTagId)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> tagService.findById(nonExistentTagId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Pet with id: " + nonExistentTagId + " not found");
    }
}