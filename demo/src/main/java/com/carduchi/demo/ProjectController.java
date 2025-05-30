package com.carduchi.demo;
import com.carduchi.demo.ProjectRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;



import java.io.InputStream;
import java.util.List;
import java.io.File;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectRepository repo;

    @Autowired
    public ProjectController(ProjectRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void initData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = new ClassPathResource("projects.json").getInputStream();
            List<Project> projects = mapper.readValue(is, new TypeReference<>() {});
            repo.saveAll(projects);
        } catch (Exception e) {
            System.err.println("❌ Failed to load data: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Project> getProjects() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Project addProject(@RequestBody Project project) {
        return repo.save(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.ok("🗑️ Project deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        return repo.findById(id).map(existingProject -> {
            existingProject.setTitle(updatedProject.getTitle());
            existingProject.setDescription(updatedProject.getDescription());
            existingProject.setGithubLink(updatedProject.getGithubLink());
            existingProject.setImageUrl(updatedProject.getImageUrl());

            Project saved = repo.save(existingProject);
            return ResponseEntity.ok(saved);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
